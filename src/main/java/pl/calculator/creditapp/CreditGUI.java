package pl.calculator.creditapp;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Route("")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class CreditGUI extends VerticalLayout {

    private IntegerField numberFieldLoan;
    private TextField textFieldYears;
    private TextField textFieldPerc;
    private TextField textFieldRata;
    private TextArea textAreaOdsetki;
    private TextArea textAreaKwota;
    private TextArea textAreaOdsetkiM;
    private TextArea textAreaKwotaM;
    private TextArea textAreaFirst;
    private TextArea textAreaLast;

    @Autowired
    private StalaRata stalaR;

    @Autowired
    private MalejacaRataService malejacaRataService;

    private MalejacaRepo malejacaRepo;

    @Autowired
    public CreditGUI(MalejacaRepo malejacaRepo){
        this.malejacaRepo = malejacaRepo;
        this.numberFieldLoan = new IntegerField("Loan amount");
        numberFieldLoan.setHelperText("Money borrowed from bank");
        this.textFieldYears = new TextField("Loan term");
        textFieldYears.setHelperText("In years");
        this.textFieldPerc = new TextField("Loan interest rate %");
        this.textFieldRata= new TextField("Monthly payment");
        this.textAreaOdsetki = new TextArea("Total interest");
        this.textAreaKwota = new TextArea("Total cost of credit");
        this.textAreaOdsetkiM = new TextArea("Wysokość odsetek rata malejąca");
        this.textAreaKwotaM = new TextArea("Całkowity koszt kredytu rata malejąca");
        this.textAreaFirst = new TextArea("First installment");
        this.textAreaLast = new TextArea("Last installment");

        Button buttonStala = new Button("Equal Installments");
        Button buttonMalejaca = new Button(" Decreasing installments");

        VerticalLayout vertical1 = new VerticalLayout(textFieldRata, textAreaOdsetki, textAreaKwota);
        vertical1.setAlignItems(Alignment.CENTER);
        VerticalLayout vertical2 = new VerticalLayout(textAreaOdsetkiM, textAreaKwotaM);
        vertical2.setAlignItems(Alignment.CENTER);

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        //Grid creation for decreasing installment

        Grid<MalejacaRata> malejacaRataGrid = new Grid<>(MalejacaRata.class);
        malejacaRataGrid.setColumns("id", "rata");

        // calculation for equal installment after pushing button

        buttonStala.addClickListener(buttonClickEvent -> {
            this.stalaR = new StalaRata(numberFieldLoan.getValue(), Integer.parseInt(textFieldYears.getValue()), Double.parseDouble(textFieldPerc.getValue()));
            textFieldRata.setValue(String.format("%.2f",stalaR.calculateRata(stalaR.getL(), stalaR.getP())));
            textAreaOdsetki.setValue(String.format("%.2f", stalaR.calculateOdsetki(stalaR.getL())));
            textAreaKwota.setValue((String.format("% .2f", stalaR.calculateCalkowitaKwotaKredytu())));
                });

        // calculation for decreasing installment after pushing button

        buttonMalejaca.addClickListener(buttonClickEvent -> {
            malejacaRepo.resetIDColumn();
            this.malejacaRataService = new MalejacaRataService(numberFieldLoan.getValue(), Integer.parseInt(textFieldYears.getValue()), Double.parseDouble(textFieldPerc.getValue()), malejacaRepo);
            malejacaRataService.calculateRata(malejacaRataService.getL(), malejacaRataService.getP());
            textAreaKwotaM.setValue(String.format("%.2f", malejacaRepo.calculateCalkowitaKwotaKredytu()));
            textAreaOdsetkiM.setValue(String.format("%.2f", malejacaRepo.calculateCalkowitaKwotaKredytu()-malejacaRataService.getL()));
            textAreaFirst.setValue(String.format("%.2f", malejacaRepo.showFirstRata()));
            textAreaLast.setValue(String.format("%.2f", malejacaRepo.showLastRata()));

            // input for grid with decreasing installment
            List<MalejacaRata> malejacaRataList = (List<MalejacaRata>) malejacaRepo.findAll();
            ListDataProvider<MalejacaRata> dataProvider = DataProvider.ofCollection(malejacaRataList);
            malejacaRataGrid.setDataProvider(dataProvider);
            add(malejacaRataGrid);

        });

        // GUI layout

        add(
                new H1("Mortage calculator"),
                new H2("Please type input data to calculate credit"),
                new HorizontalLayout(numberFieldLoan, textFieldYears, textFieldPerc),
                new HorizontalLayout(buttonStala, buttonMalejaca),
                new H2("Results for equal installments variant"),
                vertical1,
                new H2("Results for decreasing installments variant"),
                new HorizontalLayout(textAreaFirst, textAreaLast),
                vertical2
        );

    }

}
