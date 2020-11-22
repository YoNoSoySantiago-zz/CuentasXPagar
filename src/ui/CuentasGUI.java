package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import custom_exception.ValuesIsEmptyException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Company;
import model.Debt;

public class CuentasGUI {
	//Start
    @FXML
    private BorderPane mainPane;
    
    //Company
    @FXML
    private Tab tabAdd;

    @FXML
    private TextField txtAddMonto;

    @FXML
    private TextField txtAddCode;

    @FXML
    private DatePicker dateAddFechaLimte;

    @FXML
    private Button bttnAdd;

    @FXML
    private TextField txtAddProveedor;

    @FXML
    private TextField txtNumberOrder;

    @FXML
    private Tab tabCxP;

    @FXML
    private TableView<Debt> tableViewCxP;

    @FXML
    private TableColumn<Debt, Integer> columNumerOrderCxP;

    @FXML
    private TableColumn<Debt, String> columCodeCxP;

    @FXML
    private TableColumn<Debt, Double> columMontoCxP;

    @FXML
    private TableColumn<Debt, String> columProveedorCxP;

    @FXML
    private TableColumn<Debt, LocalDate> columFechaCxP;

    @FXML
    private Button btnModificar;

    @FXML
    private TextField txtFiledSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private MenuButton menuToSearch;

    @FXML
    private Button btnShow30days;

    @FXML
    private Button btnShow60days;

    @FXML
    private Button btnShow90ays;

    @FXML
    private Tab tabRegistro;
    
    @FXML
    private Button btnLogOut;

    @FXML
    private TableView<Debt> tableViewRegistro;
    
    @FXML
    private TableColumn<Debt, Integer> columNumerOrderR;

    @FXML
    private TableColumn<Debt, LocalDateTime> columDateR;

    @FXML
    private TableColumn<Debt, String> columAccionR;

    @FXML
    private TableColumn<Debt, String> columCodeR;

    @FXML
    private TableColumn<Debt, Double> columMontoR;

    @FXML
    private TableColumn<Debt, String> columProveedorR;

    @FXML
    private TableColumn<Debt, LocalDate> columFechaR;

    @FXML
    private Tab tabMod;

    @FXML
    private TextField txtModNombre;

    @FXML
    private TextField txtModMonto;

    @FXML
    private Button btnConfirmarMod;

    @FXML
    private DatePicker dateModFechaLimite;

    @FXML
    private TextField txtModProveedor;

    @FXML
    private TextField txtModNumberOrder;
    
    private ArrayList<Company> allCompanies;
    private Stage window;
    private Company currentCompany;
    
    public static final String FILE_NAME = "allCompanies.data";
    
    @SuppressWarnings("unchecked")
	public CuentasGUI(Stage w) {
		window = w;
		allCompanies = new ArrayList<>();
		File file = new File(FILE_NAME);
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			allCompanies = (ArrayList<Company>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			System.out.println("No exist by now");
		} catch (ClassNotFoundException e) {
			System.out.println("No exist by now");
		} catch (IOException e) {
			System.out.println("No exist by now");
		}
	}
    
    public void init() {
    	try {
			loadCreateCompany();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void addCuenta(ActionEvent event) throws ValuesIsEmptyException {
    	if(txtAddCode.getText().isEmpty()||txtAddMonto.getText().isEmpty()||txtAddProveedor.getText().isEmpty()||dateAddFechaLimte.getValue()==null) {
    		throw new ValuesIsEmptyException();
    	}
    	if(dateAddFechaLimte.getValue().isBefore(LocalDate.now())) {
    		throw new ValuesIsEmptyException();
    	}
    	double amountToPay=0;
    	try {
    		amountToPay = Double.parseDouble(txtAddMonto.getText());
    	}catch(NumberFormatException e) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERROR");
    		alert.setHeaderText("CxP");
    		alert.setContentText("Utiliza solamente numeros con '.' como separador decimal ");
    		alert.showAndWait();
    	}
    	String debtCode = txtAddCode.getText();
    	String provider = txtAddProveedor.getText();
    	LocalDate dateToPay = dateAddFechaLimte.getValue();
    	currentCompany.addDebt(amountToPay, debtCode, provider, dateToPay);
    }

    @FXML
    void btnShow30days(Event event) {

    }

    @FXML
    void btnShow60days(Event event) {

    }

    @FXML
    void btnShow90ays(Event event) {

    }

    @FXML
    void confirmarMod(Event event) {

    }

    @FXML
    void modificarCuenta(Event event) {

    }
    @FXML
    void onTabAdd(Event event) {

    }

    @FXML
    void onTabCxP(Event event) {

    }

    @FXML
    void onTabMod(Event event) {

    }

    @FXML
    void onTabR(Event event) {

    }
    
    @FXML
    void logOut(Event event) throws IOException {
    	saveInfo();
    	loadCreateCompany();
    }
    
    //Create Company
    @FXML
    private Button btnLogIn;

    @FXML
    private TextField txtCompanyName;

    @FXML
    void onLogIn(ActionEvent event) throws ValuesIsEmptyException {
    	if(txtCompanyName.getText().isEmpty()) {
    		throw new ValuesIsEmptyException();
    	}
    	currentCompany = new Company(txtCompanyName.getText());
    	for (int i = 0; i < allCompanies.size(); i++) {
    		if(allCompanies.get(i).getCompName().equalsIgnoreCase(txtCompanyName.getText())){
    			currentCompany = allCompanies.get(i);
    			break;
    		}
		}
    	try {
			loadCxP();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
     
    
    //Loads
    private void loadCreateCompany() throws IOException {
    	FXMLLoader fxmlLoad = new FXMLLoader(getClass().getResource("createCompanyFX.fxml"));
    	fxmlLoad.setController(this);
    	Parent pane = fxmlLoad.load();
    	mainPane.getChildren().clear();
    	mainPane.setCenter(pane);
    }
    
    private void loadCxP() throws IOException {
    	FXMLLoader fxmlLoad = new FXMLLoader(getClass().getResource("cuentasXpagar.fxml"));
    	fxmlLoad.setController(this);
    	Parent pane = fxmlLoad.load();
    	mainPane.getChildren().clear();
    	mainPane.setCenter(pane);
    }
    
    private void saveInfo() throws FileNotFoundException, IOException {
    	File file = new File(FILE_NAME);
    	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
    	oos.writeObject(allCompanies);
    	oos.close();
    }
   
    //Close
    public void initialize() {
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				System.out.println("Closing the window!");
				try {
					saveInfo();
				} catch (FileNotFoundException e) {
					System.out.println("No se pudo guardar");
				} catch (IOException e) {
					System.out.println("No se pudo guardar");
				}
			}
		});

	}
}