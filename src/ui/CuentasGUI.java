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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Company;
import model.Debt;
import model.Record;

public class CuentasGUI {
	//Start
	@FXML
	private BorderPane mainPane;

	//Company

	@FXML
	private TabPane myTabPane;

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
	private TableView<Record> tableViewRegistro;

	@FXML
	private TableColumn<Record, Integer> columNumerOrderR;

	@FXML
	private TableColumn<Record, LocalDateTime> columDateR;

	@FXML
	private TableColumn<Record, String> columAccionR;

	@FXML
	private TableColumn<Record, String> columCodeR;

	@FXML
	private TableColumn<Record, Double> columMontoR;

	@FXML
	private TableColumn<Record, String> columProveedorR;

	@FXML
	private TableColumn<Record, LocalDate> columFechaR;

	@FXML
	private Tab tabMod;

	@FXML
	private TextField txtModCodigo;

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

	@FXML
	private ComboBox<String> menuToSearch;

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
			try {
				throw new ValuesIsEmptyException();
			}catch(ValuesIsEmptyException e) {
				
			}
		}
		if(dateAddFechaLimte.getValue().isBefore(LocalDate.now())) {
			try {
				throw new ValuesIsEmptyException();
			}catch(ValuesIsEmptyException e) {
				
			}
		}
		double amountToPay=0;
		try {
			amountToPay = Double.parseDouble(txtAddMonto.getText());
			String debtCode = txtAddCode.getText();
			String provider = txtAddProveedor.getText();
			LocalDate dateToPay = dateAddFechaLimte.getValue();
			currentCompany.addDebt(amountToPay, debtCode, provider, dateToPay);
			txtAddCode.clear();
			txtAddMonto.clear();
			txtAddProveedor.clear();
		}catch(NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("CxP");
			alert.setContentText("Utiliza solamente numeros con '.' como separador decimal ");
			alert.showAndWait();
		}

		txtNumberOrder.setText(currentCompany.getCurrentNumber()+"");

	}

	@FXML
	void btnShow30days(Event event) {
		ArrayList<Debt> debts = currentCompany.thirtyDaysDebts();
		inicializateTv(debts);

	}

	@FXML
	void btnShow60days(Event event) {
		ArrayList<Debt> debts = currentCompany.sixtyDaysDebts();
		inicializateTv(debts);
	}

	@FXML
	void btnShow90ays(Event event) {
		ArrayList<Debt> debts = currentCompany.ninetyDaysDebts();
		inicializateTv(debts);
	}

	@FXML
	void confirmarMod(Event event) throws ValuesIsEmptyException  {

		if(txtModCodigo.getText().isEmpty()||txtModMonto.getText().isEmpty()||txtModProveedor.getText().isEmpty()||dateModFechaLimite.getValue()==null) {
			try {
				throw new ValuesIsEmptyException();
			}catch(ValuesIsEmptyException e) {
				
			}
		}
		if(dateModFechaLimite.getValue().isBefore(LocalDate.now())) {
			try {
				throw new ValuesIsEmptyException();
			}catch(ValuesIsEmptyException e) {
				
			}
		}
		double amountToPay=0;
		try {
			amountToPay = Double.parseDouble(txtModMonto.getText());
		}catch(NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("CxP");
			alert.setContentText("Utiliza solamente numeros con '.' como separador decimal ");
			alert.showAndWait();
		}
		String debtCode = txtModCodigo.getText();
		String provider = txtModProveedor.getText();
		LocalDate dateToPay = dateAddFechaLimte.getValue();
		currentCompany.modDebt(tableViewCxP.getSelectionModel().getSelectedItem(), amountToPay, debtCode, provider, dateToPay);
		txtModCodigo.clear();
		txtModMonto.clear();
		txtModProveedor.clear();
		
		tableViewCxP.getItems().clear();
		inicializateTv(currentCompany.getMyDebts());
		myTabPane.getSelectionModel().select(tabCxP);
		tabMod.setDisable(true);


		
		

	}

	@FXML
	void modificarCuenta(Event event) {

		if (tableViewCxP.getSelectionModel().getSelectedItem()!=null){
			myTabPane.getSelectionModel().select(tabMod);
			tabMod.setDisable(false);
			Debt temp = tableViewCxP.getSelectionModel().getSelectedItem();

			txtModNumberOrder.setText(""+temp.getNumberOrder());
			txtModCodigo.setText(temp.getDebtCode());
			txtModMonto.setText(""+temp.getAmountToPay());
			txtModProveedor.setText(temp.getProvider());
			dateModFechaLimite.setValue(temp.getDateToPay());
			

		}

		else {
			System.out.println("Haceer una excepci�n :v");	
		}

	}
	@FXML
	void onTabAdd(Event event) {

	}

	public void inicializateTv(ArrayList<Debt> ds) {
		
		ObservableList<Debt> observableList;
		observableList = FXCollections.observableArrayList(ds);

		tableViewCxP.setItems(observableList);
		columNumerOrderCxP.setCellValueFactory(new PropertyValueFactory<Debt, Integer>("numberOrder"));
		columCodeCxP.setCellValueFactory(new PropertyValueFactory<Debt, String>("debtCode"));
		columMontoCxP.setCellValueFactory(new PropertyValueFactory<Debt, Double>("amountToPay"));
		columProveedorCxP.setCellValueFactory(new PropertyValueFactory<Debt, String>("provider"));
		columFechaCxP.setCellValueFactory(new PropertyValueFactory<Debt,LocalDate >("dateToPay"));


	}

	@FXML
	void onTabCxP(Event event) {
		inicializateTv(currentCompany.getMyDebts());
	}

	@FXML
	void onTabMod(Event event) {

	}

	public void inicializateTvRegistry(ArrayList<Record> re) {
		
		ObservableList<Record> observableList;
		observableList = FXCollections.observableArrayList(re);

		tableViewRegistro.setItems(observableList);
		columNumerOrderR.setCellValueFactory(new PropertyValueFactory<Record, Integer >("numberOrder"));
		columCodeR.setCellValueFactory(new PropertyValueFactory<Record, String>("debtCode"));
		columMontoR.setCellValueFactory(new PropertyValueFactory<Record, Double>("amountToPay"));
		columProveedorR.setCellValueFactory(new PropertyValueFactory<Record, String>("provider"));
		columFechaR.setCellValueFactory(new PropertyValueFactory<Record,LocalDate >("dateToPay"));
		columDateR.setCellValueFactory(new PropertyValueFactory<Record,LocalDateTime >("time"));
		columAccionR.setCellValueFactory(new PropertyValueFactory<Record,String >("action"));


	}

	@FXML
	void onTabR(Event event) {
		inicializateTvRegistry(currentCompany.getRecord());
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

	@FXML
	public void btnSearch(ActionEvent event) throws ValuesIsEmptyException {
		System.out.println("metodo");
		if(!menuToSearch.getSelectionModel().getSelectedItem().isEmpty()) {
			String search = menuToSearch.getSelectionModel().getSelectedItem();

			if(!txtFiledSearch.getText().isEmpty()) {
				String toSearch = txtFiledSearch.getText();
				ArrayList<Debt> debts = currentCompany.search(search,toSearch);
				inicializateTv(debts);
			}else {
				try {
					throw new ValuesIsEmptyException();
				}catch(ValuesIsEmptyException e) {
					
				}
				
			}

		}else {
			try {
				throw new ValuesIsEmptyException();
			}catch(ValuesIsEmptyException e) {
				
			}
			
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
		txtNumberOrder.setText(currentCompany.getCurrentNumber()+"");
		menuToSearch.getItems().removeAll();
		menuToSearch.getItems().addAll(Company.CODE,Company.ORDER,Company.PROVIDER,Company.DATE);
		menuToSearch.getSelectionModel().select(Company.CODE);;
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