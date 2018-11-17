/**
 * 
 */
package controllers.players;

import com.jfoenix.controls.JFXTextField;
import domains.Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.lang.StringUtils;
import utils.WorldCupUtils;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;


/**
 * @author ruitex23
 *
 */
public class AddEditPlayerController {

	@FXML ImageView profilePictureIV;
	@FXML MenuButton menuButton;
	@FXML private ChoiceBox<String> confederationsCB;
	@FXML private ImageView flagIV;
	@FXML private Label countryLabel;
	@FXML private JFXTextField lastnameTF;
	@FXML private JFXTextField firstnameTF;
	@FXML private JFXTextField ageTF;
	@FXML private Button addEditButton;

	private Stage primaryStage;
	private ConsultPlayersController previousController;
	final FileChooser fileChooser = new FileChooser();
	private Player player;
	private HashMap<String, String> countriesHM;

	public void setStage(Stage stage) {
		this.primaryStage = stage;
	}

	public void setPreviousController(ConsultPlayersController previousController) {
		this.previousController = previousController;
	}

	public void setupScreen() {
		player = new Player();
		setupFlags();
		initializeCountries();
		confederationsCB.setItems(FXCollections.observableArrayList("Confederation", "ACC", "CPB", "ACBC", "CEB"));
		confederationsCB.getSelectionModel().selectFirst();
		ageTF.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					ageTF.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
	}
	
	public void setupEditScreen(Player player) {
		this.player = player;
		lastnameTF.setText(this.player.getLastName());
		firstnameTF.setText(this.player.getFirstName());
		if(player.getAge() != null) {
			ageTF.setText(this.player.getAge().toString());
		}
		confederationsCB.getSelectionModel().select(this.player.getConfederation());
		if(StringUtils.isNotBlank(player.getCountry())) {
			flagIV.setImage(new Image("/main/resources/flags-normal/" +
					WorldCupUtils.getCountryCodeByDescription(this.player.getCountry()).toLowerCase()+ ".png"));
			countryLabel.setText(this.player.getCountry());
		}
		if(StringUtils.isNotBlank(player.getProfilePicturePath())) {
			profilePictureIV.setImage(new Image(player.getProfilePicturePath()));
		}
		addEditButton.setText("Update");
	}

	@FXML private void choosePicture(ActionEvent event) {
		configureFileChooser(fileChooser);
		fileChooser.setTitle("Save Image");
		File file = fileChooser.showOpenDialog(primaryStage);
		if (file != null) {
			openFile(file);
		}
	}
	
	@FXML private void addPlayer() {
		System.out.println("1");
		if(StringUtils.isNotBlank(lastnameTF.getText())) {
			player.setLastName(lastnameTF.getText());
			if(StringUtils.isNotBlank(ageTF.getText())) {
				player.setAge(Integer.valueOf(ageTF.getText()));
			}
			if(StringUtils.isNotBlank(firstnameTF.getText())) {
				player.setFirstName(firstnameTF.getText());
			}
			if(!confederationsCB.getSelectionModel().isSelected(0)) {
				player.setConfederation(confederationsCB.getSelectionModel().getSelectedItem());
			}
			System.out.println("2");
			if(addEditButton.getText().compareToIgnoreCase("update") == 0) {
				previousController.backFromEditPlayer(player);
			} else {
				previousController.backFromAddPlayer(player);
			}
		} else {
			Alert alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
			alert.setTitle("Houston we have a problem!");
			alert.setHeaderText("No Last Name?");
			alert.setContentText("It's mandatory for us to have his/her last name...");
			alert.showAndWait();
		}
		
	}

	private static void configureFileChooser(final FileChooser fileChooser) {      
		fileChooser.setTitle("View Pictures");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))	);                 
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All Images", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"),
				new FileChooser.ExtensionFilter("PNG", "*.png")
				);
	}

	private void openFile(File file) {
//		File newFile = new File(getClass().getResource("/main/resources/" + lastnameTF.getText() + ".jpg").toExternalForm());
//		file.renameTo(newFile);
//		System.out.println(newFile.toURI().toString());
//		player.setProfilePicturePath(newFile.toURI().toString());
//		Image image = new Image(newFile.toURI().toString());
//		profilePictureIV.setImage(image);
	}

	private ImageView getIcon(String resourcePath) {
		InputStream input = getClass().getResourceAsStream(resourcePath);
		Image image = new Image(input);
		return new ImageView(image);
	}
	
	private void initializeCountries() {
		countriesHM = new LinkedHashMap<String, String>();
		countriesHM.put(WorldCupUtils.ARGENTINA_CODE, WorldCupUtils.ARGENTINA_DESC);
		countriesHM.put(WorldCupUtils.AUSTRALIA_CODE, WorldCupUtils.AUSTRALIA_DESC);
		countriesHM.put(WorldCupUtils.AUSTRIA_CODE, WorldCupUtils.AUSTRIA_DESC);
		countriesHM.put(WorldCupUtils.BELGIUM_CODE, WorldCupUtils.BELGIUM_DESC);
		countriesHM.put(WorldCupUtils.BOLIVIA_CODE, WorldCupUtils.BOLIVIA_DESC);
		countriesHM.put(WorldCupUtils.BRASIL_CODE, WorldCupUtils.BRASIL_DESC);
		countriesHM.put(WorldCupUtils.COLOMBIA_CODE, WorldCupUtils.COLOMBIA_DESC);
		countriesHM.put(WorldCupUtils.COSTARICA_CODE, WorldCupUtils.COSTARICA_DESC);
		countriesHM.put(WorldCupUtils.CYPRUS_CODE, WorldCupUtils.CYPRUS_DESC);
		countriesHM.put(WorldCupUtils.CZECHREP_CODE, WorldCupUtils.CZECHREP_DESC);
		countriesHM.put(WorldCupUtils.DENMARK_CODE, WorldCupUtils.DENMARK_DESC);
		countriesHM.put(WorldCupUtils.ECUADOR_CODE, WorldCupUtils.ECUADOR_DESC);
		countriesHM.put(WorldCupUtils.EGYPT_CODE, WorldCupUtils.EGYPT_DESC);
		countriesHM.put(WorldCupUtils.FINLAND_CODE, WorldCupUtils.FINLAND_DESC);
		countriesHM.put(WorldCupUtils.FRANCE_CODE, WorldCupUtils.FRANCE_DESC);
		countriesHM.put(WorldCupUtils.GERMANY_CODE, WorldCupUtils.GERMANY_DESC);
		countriesHM.put(WorldCupUtils.GREECE_CODE, WorldCupUtils.GREECE_DESC);
		countriesHM.put(WorldCupUtils.GUATEMALA_CODE, WorldCupUtils.GUATEMALA_DESC);
		countriesHM.put(WorldCupUtils.ITALY_CODE, WorldCupUtils.ITALY_DESC);
		countriesHM.put(WorldCupUtils.JAPAN_CODE, WorldCupUtils.JAPAN_DESC);
		countriesHM.put(WorldCupUtils.JORDAN_CODE, WorldCupUtils.JORDAN_DESC);
		countriesHM.put(WorldCupUtils.LUXEMBOURG_CODE, WorldCupUtils.LUXEMBOURG_DESC);
		countriesHM.put(WorldCupUtils.MEXICO_CODE, WorldCupUtils.MEXICO_DESC);
		countriesHM.put(WorldCupUtils.NETHERLANDS_CODE, WorldCupUtils.NETHERLANDS_DESC);
		countriesHM.put(WorldCupUtils.PERU_CODE, WorldCupUtils.PERU_DESC);
		countriesHM.put(WorldCupUtils.PORTUGAL_CODE, WorldCupUtils.PORTUGAL_DESC);
		countriesHM.put(WorldCupUtils.SOUTHKOREA_CODE, WorldCupUtils.SOUTHKOREA_DESC);
		countriesHM.put(WorldCupUtils.SPAIN_CODE, WorldCupUtils.SPAIN_DESC);
		countriesHM.put(WorldCupUtils.SWEDEN_CODE, WorldCupUtils.SWEDEN_DESC);
		countriesHM.put(WorldCupUtils.SWITZERLAND_CODE, WorldCupUtils.SWITZERLAND_DESC);
		countriesHM.put(WorldCupUtils.TURKEY_CODE, WorldCupUtils.TURKEY_DESC);
		countriesHM.put(WorldCupUtils.UK_CODE, WorldCupUtils.UK_DESC);
		countriesHM.put(WorldCupUtils.US_CODE, WorldCupUtils.US_DESC);
		countriesHM.put(WorldCupUtils.VENEZUELA_CODE, WorldCupUtils.VENEZUELA_DESC);
		countriesHM.put(WorldCupUtils.VIETNAM_CODE, WorldCupUtils.VIETNAM_DESC);
	}

	/**
	 * 
	 */
	private void setupFlags() {
		//Argentina
		ImageView imageViewAR = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewAR.setFitHeight(23.0);
		imageViewAR.setFitWidth(35.0);
		MenuItem menuItemAR = new MenuItem(WorldCupUtils.ARGENTINA_CODE, imageViewAR);
		menuItemAR.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemAR.getText()));
		    System.out.println("menuItemAR");
		    player.setCountry(WorldCupUtils.ARGENTINA_DESC);
		});
		menuButton.getItems().add(menuItemAR);
		//Austria
		ImageView imageViewAT = this.getIcon(getClass().getResource("/flags-mini/at.png").toString());
		imageViewAT.setFitHeight(23.0);
		imageViewAT.setFitWidth(35.0);
		MenuItem menuItemAT = new MenuItem(WorldCupUtils.AUSTRIA_CODE, imageViewAT);
		menuButton.getItems().add(menuItemAT);
		menuItemAT.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemAT.getText()));
		    System.out.println("menuItemAT");
		    player.setCountry(WorldCupUtils.AUSTRIA_DESC);
		});
		//Australia
		ImageView imageViewAU = this.getIcon(getClass().getResource("/flags-mini/au.png").toString());
		imageViewAU.setFitHeight(23.0);
		imageViewAU.setFitWidth(35.0);
		MenuItem menuItemAU = new MenuItem(WorldCupUtils.AUSTRALIA_CODE, imageViewAU);
		menuButton.getItems().add(menuItemAU);
		menuItemAU.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemAU.getText()));
		    System.out.println("menuItemAU");
		    player.setCountry(WorldCupUtils.AUSTRALIA_DESC);
		});
		//Belgica
		ImageView imageViewBE = this.getIcon(getClass().getResource("/flags-mini/be.png").toString());
		imageViewBE.setFitHeight(23.0);
		imageViewBE.setFitWidth(35.0);
		MenuItem menuItemBE = new MenuItem(WorldCupUtils.BELGIUM_CODE, imageViewBE);
		menuButton.getItems().add(menuItemBE);
		menuItemBE.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemBE.getText()));
		    System.out.println("menuItemBE");
		    player.setCountry(WorldCupUtils.BELGIUM_DESC);
		});
		//Bolivia
		ImageView imageViewBO = this.getIcon(getClass().getResource("/flags-mini/bo.png").toString());
		imageViewBO.setFitHeight(23.0);
		imageViewBO.setFitWidth(35.0);
		MenuItem menuItemBO = new MenuItem(WorldCupUtils.BOLIVIA_CODE, imageViewBO);
		menuButton.getItems().add(menuItemBO);
		menuItemBO.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemBO.getText()));
		    System.out.println("menuItemBO");
		    player.setCountry(WorldCupUtils.BOLIVIA_DESC);
		});
		//Brasil
		ImageView imageViewBR = this.getIcon(getClass().getResource("/flags-mini/br.png").toString());
		imageViewBR.setFitHeight(23.0);
		imageViewBR.setFitWidth(35.0);
		MenuItem menuItemBR = new MenuItem(WorldCupUtils.BRASIL_CODE, imageViewBR);
		menuButton.getItems().add(menuItemBR);
		menuItemBR.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemBR.getText()));
		    System.out.println("menuItemBR");
		    player.setCountry(WorldCupUtils.BRASIL_DESC);
		});
		//Czech Republic
		ImageView imageViewCZ = this.getIcon(getClass().getResource("/flags-mini/cz.png").toString());
		imageViewCZ.setFitHeight(23.0);
		imageViewCZ.setFitWidth(35.0);
		MenuItem menuItemCZ = new MenuItem(WorldCupUtils.CZECHREP_CODE, imageViewCZ);
		menuButton.getItems().add(menuItemCZ);
		menuItemCZ.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemCZ.getText()));
		    System.out.println("menuItemCZ");
		    player.setCountry(WorldCupUtils.CZECHREP_DESC);
		});
		//Cyprus
		ImageView imageViewCY = this.getIcon(getClass().getResource("/flags-mini/cy.png").toString());
		imageViewCY.setFitHeight(23.0);
		imageViewCY.setFitWidth(35.0);
		MenuItem menuItemCY = new MenuItem(WorldCupUtils.CYPRUS_CODE, imageViewCY);
		menuButton.getItems().add(menuItemCY);
		menuItemCY.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemCY.getText()));
		    System.out.println("menuItemCY");
		    player.setCountry(WorldCupUtils.CYPRUS_DESC);
		});
		//Colombia
		ImageView imageViewCO = this.getIcon(getClass().getResource("/flags-mini/co.png").toString());
		imageViewCO.setFitHeight(23.0);
		imageViewCO.setFitWidth(35.0);
		MenuItem menuItemCO = new MenuItem(WorldCupUtils.COLOMBIA_CODE, imageViewCO);
		menuButton.getItems().add(menuItemCO);
		menuItemCO.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemCO.getText()));
		    System.out.println("menuItemCO");
		    player.setCountry(WorldCupUtils.COLOMBIA_DESC);
		});
		//Costa Rica
		ImageView imageViewCR = this.getIcon(getClass().getResource("/flags-mini/cr.png").toString());
		imageViewCR.setFitHeight(23.0);
		imageViewCR.setFitWidth(35.0);
		MenuItem menuItemCR = new MenuItem(WorldCupUtils.COSTARICA_CODE, imageViewCR);
		menuButton.getItems().add(menuItemCR);
		menuItemCR.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemCR.getText()));
		    System.out.println("menuItemCR");
		    player.setCountry(WorldCupUtils.COSTARICA_DESC);
		});
		//Denmark
		ImageView imageViewDK = this.getIcon(getClass().getResource("/flags-mini/dk.png").toString());
		imageViewDK.setFitHeight(23.0);
		imageViewDK.setFitWidth(35.0);
		MenuItem menuItemDK = new MenuItem(WorldCupUtils.DENMARK_CODE, imageViewDK);
		menuButton.getItems().add(menuItemDK);
		menuItemDK.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemDK.getText()));
		    System.out.println("menuItemDK");
		    player.setCountry(WorldCupUtils.DENMARK_DESC);
		});
		//Ecuador
		ImageView imageViewEC = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewEC.setFitHeight(23.0);
		imageViewEC.setFitWidth(35.0);
		MenuItem menuItemEC = new MenuItem(WorldCupUtils.ECUADOR_CODE, imageViewEC);
		menuButton.getItems().add(menuItemEC);
		menuItemEC.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemEC.getText()));
		    System.out.println("menuItemEC");
		    player.setCountry(WorldCupUtils.ECUADOR_DESC);
		});
		//Egypt
		ImageView imageViewEG = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewEG.setFitHeight(23.0);
		imageViewEG.setFitWidth(35.0);
		MenuItem menuItemEG = new MenuItem(WorldCupUtils.EGYPT_CODE, imageViewEG);
		menuButton.getItems().add(menuItemEG);
		menuItemEG.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemEG.getText()));
		    System.out.println("menuItemEG");
		    player.setCountry(WorldCupUtils.EGYPT_DESC);
		});
		//France
		ImageView imageViewFR = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewFR.setFitHeight(23.0);
		imageViewFR.setFitWidth(35.0);
		MenuItem menuItemFR = new MenuItem(WorldCupUtils.FRANCE_CODE, imageViewFR);
		menuButton.getItems().add(menuItemFR);
		menuItemFR.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemFR.getText()));
		    System.out.println("menuItemFR");
		    player.setCountry(WorldCupUtils.FRANCE_DESC);
		});
		//Finland
		ImageView imageViewFI = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewFI.setFitHeight(23.0);
		imageViewFI.setFitWidth(35.0);
		MenuItem menuItemFI = new MenuItem(WorldCupUtils.FINLAND_CODE, imageViewFI);
		menuButton.getItems().add(menuItemFI);
		menuItemFI.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemFI.getText()));
		    System.out.println("menuItemFI");
		    player.setCountry(WorldCupUtils.FINLAND_DESC);
		});
		//Germany
		ImageView imageViewDE = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewDE.setFitHeight(23.0);
		imageViewDE.setFitWidth(35.0);
		MenuItem menuItemDE = new MenuItem(WorldCupUtils.GERMANY_CODE, imageViewDE);
		menuButton.getItems().add(menuItemDE);
		menuItemDE.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemDE.getText()));
		    System.out.println("menuItemDE");
		    player.setCountry(WorldCupUtils.GERMANY_DESC);
		});
		//Greece
		ImageView imageViewGR = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewGR.setFitHeight(23.0);
		imageViewGR.setFitWidth(35.0);
		MenuItem menuItemGR = new MenuItem(WorldCupUtils.GREECE_CODE, imageViewGR);
		menuButton.getItems().add(menuItemGR);
		menuItemGR.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemGR.getText()));
		    System.out.println("menuItemGR");
		    player.setCountry(WorldCupUtils.GREECE_DESC);
		});
		//Guatemala
		ImageView imageViewGT = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewGT.setFitHeight(23.0);
		imageViewGT.setFitWidth(35.0);
		MenuItem menuItemGT = new MenuItem(WorldCupUtils.GUATEMALA_CODE, imageViewGT);
		menuButton.getItems().add(menuItemGT);
		menuItemGT.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemGT.getText()));
		    System.out.println("menuItemGT");
		    player.setCountry(WorldCupUtils.GUATEMALA_DESC);
		});
		//Italy
		ImageView imageViewIT = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewIT.setFitHeight(23.0);
		imageViewIT.setFitWidth(35.0);
		MenuItem menuItemIT = new MenuItem(WorldCupUtils.ITALY_CODE, imageViewIT);
		menuButton.getItems().add(menuItemIT);
		menuItemIT.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemIT.getText()));
		    System.out.println("menuItemIT");
		    player.setCountry(WorldCupUtils.ITALY_DESC);
		});
		//Japan
		ImageView imageViewJP = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewJP.setFitHeight(23.0);
		imageViewJP.setFitWidth(35.0);
		MenuItem menuItemJP = new MenuItem(WorldCupUtils.JAPAN_CODE, imageViewJP);
		menuButton.getItems().add(menuItemJP);
		menuItemJP.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemJP.getText()));
		    System.out.println("menuItemJP");
		    player.setCountry(WorldCupUtils.JAPAN_DESC);
		});
		//Jordan
		ImageView imageViewJO = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewJO.setFitHeight(23.0);
		imageViewJO.setFitWidth(35.0);
		MenuItem menuItemJO = new MenuItem(WorldCupUtils.JORDAN_CODE, imageViewJO);
		menuButton.getItems().add(menuItemJO);
		menuItemJO.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemJO.getText()));
		    System.out.println("menuItemJO");
		    player.setCountry(WorldCupUtils.JORDAN_DESC);
		});
		//Luxembourg
		ImageView imageViewLU = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewLU.setFitHeight(23.0);
		imageViewLU.setFitWidth(35.0);
		MenuItem menuItemLU = new MenuItem(WorldCupUtils.LUXEMBOURG_CODE, imageViewLU);
		menuButton.getItems().add(menuItemLU);
		menuItemLU.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemLU.getText()));
		    System.out.println("menuItemLU");
		    player.setCountry(WorldCupUtils.LUXEMBOURG_DESC);
		});
		//Mexico
		ImageView imageViewMX = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewMX.setFitHeight(23.0);
		imageViewMX.setFitWidth(35.0);
		MenuItem menuItemMX = new MenuItem(WorldCupUtils.MEXICO_CODE, imageViewMX);
		menuButton.getItems().add(menuItemMX);
		menuItemMX.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemMX.getText()));
		    System.out.println("menuItemMX");
		    player.setCountry(WorldCupUtils.MEXICO_DESC);
		});
		//Netherlands
		ImageView imageViewNL = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewNL.setFitHeight(23.0);
		imageViewNL.setFitWidth(35.0);
		MenuItem menuItemNL = new MenuItem(WorldCupUtils.NETHERLANDS_CODE, imageViewNL);
		menuButton.getItems().add(menuItemNL);
		menuItemNL.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemNL.getText()));
		    System.out.println("menuItemNL");
		    player.setCountry(WorldCupUtils.NETHERLANDS_DESC);
		});
		//Portugal
		ImageView imageViewPT = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewPT.setFitHeight(23.0);
		imageViewPT.setFitWidth(35.0);
		MenuItem menuItemPT = new MenuItem(WorldCupUtils.PORTUGAL_CODE, imageViewPT);
		menuButton.getItems().add(menuItemPT);
		menuItemPT.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemPT.getText()));
		    System.out.println("menuItemPT");
		    player.setCountry(WorldCupUtils.PORTUGAL_DESC);
		});
		//Peru
		ImageView imageViewPE = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewPE.setFitHeight(23.0);
		imageViewPE.setFitWidth(35.0);
		MenuItem menuItemPE = new MenuItem(WorldCupUtils.PERU_CODE, imageViewPE);
		menuButton.getItems().add(menuItemPE);
		menuItemPE.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemPE.getText()));
		    System.out.println("menuItemPE");
		    player.setCountry(WorldCupUtils.PERU_DESC);
		});
		//Sweden
		ImageView imageViewSE = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewSE.setFitHeight(23.0);
		imageViewSE.setFitWidth(35.0);
		MenuItem menuItemSE = new MenuItem(WorldCupUtils.SWEDEN_CODE, imageViewSE);
		menuButton.getItems().add(menuItemSE);
		menuItemSE.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemSE.getText()));
		    System.out.println("menuItemSE");
		    player.setCountry(WorldCupUtils.SWEDEN_DESC);
		});
		//Spain
		ImageView imageViewES = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewES.setFitHeight(23.0);
		imageViewES.setFitWidth(35.0);
		MenuItem menuItemES = new MenuItem(WorldCupUtils.SPAIN_CODE, imageViewES);
		menuButton.getItems().add(menuItemES);
		menuItemES.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemES.getText()));
		    System.out.println("menuItemES");
		    player.setCountry(WorldCupUtils.SPAIN_DESC);
		});
		//Switzerland
		ImageView imageViewCH = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewCH.setFitHeight(23.0);
		imageViewCH.setFitWidth(35.0);
		MenuItem menuItemCH = new MenuItem(WorldCupUtils.SWITZERLAND_CODE, imageViewCH);
		menuButton.getItems().add(menuItemCH);
		menuItemCH.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemCH.getText()));
		    System.out.println("menuItemCH");
		    player.setCountry(WorldCupUtils.SWITZERLAND_DESC);
		});
		//South Korea
		ImageView imageViewKP = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewKP.setFitHeight(23.0);
		imageViewKP.setFitWidth(35.0);
		MenuItem menuItemKP = new MenuItem(WorldCupUtils.SOUTHKOREA_CODE, imageViewKP);
		menuButton.getItems().add(menuItemKP);
		menuItemKP.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemKP.getText()));
		    System.out.println("menuItemKP");
		    player.setCountry(WorldCupUtils.SOUTHKOREA_DESC);
		});
		//Turkey
		ImageView imageViewTR = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewTR.setFitHeight(23.0);
		imageViewTR.setFitWidth(35.0);
		MenuItem menuItemTR = new MenuItem(WorldCupUtils.TURKEY_CODE, imageViewTR);
		menuButton.getItems().add(menuItemTR);
		menuItemTR.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemTR.getText()));
		    System.out.println("menuItemTR");
		    player.setCountry(WorldCupUtils.TURKEY_DESC);
		});
		//US
		ImageView imageViewUS = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewUS.setFitHeight(23.0);
		imageViewUS.setFitWidth(35.0);
		MenuItem menuItemUS = new MenuItem(WorldCupUtils.US_CODE, imageViewUS);
		menuButton.getItems().add(menuItemUS);
		menuItemUS.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemUS.getText()));
		    System.out.println("menuItemUS");
		    player.setCountry(WorldCupUtils.US_DESC);
		});
		//Vietnam
		ImageView imageViewVN = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewVN.setFitHeight(23.0);
		imageViewVN.setFitWidth(35.0);
		MenuItem menuItemVN = new MenuItem(WorldCupUtils.VIETNAM_CODE, imageViewVN);
		menuButton.getItems().add(menuItemVN);
		menuItemVN.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemVN.getText()));
		    System.out.println("menuItemVN");
		    player.setCountry(WorldCupUtils.VIETNAM_DESC);
		});
		//Venezuela
		ImageView imageViewVE = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewVE.setFitHeight(23.0);
		imageViewVE.setFitWidth(35.0);
		MenuItem menuItemVE = new MenuItem(WorldCupUtils.VENEZUELA_CODE, imageViewVE);
		menuButton.getItems().add(menuItemVE);
		menuItemVE.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemVE.getText()));
		    System.out.println("menuItemVE");
		    player.setCountry(WorldCupUtils.VENEZUELA_DESC);
		});
		//United Kingdom
		ImageView imageViewGB = this.getIcon(getClass().getResource("/flags-mini/ar.png").toString());
		imageViewGB.setFitHeight(23.0);
		imageViewGB.setFitWidth(35.0);
		MenuItem menuItemGB = new MenuItem(WorldCupUtils.UK_CODE, imageViewGB);
		menuButton.getItems().add(menuItemGB);
		menuItemGB.setOnAction(event -> {
			flagIV.setImage(new Image(getClass().getResource("/flags-normal/ar.png").toString()));
			countryLabel.setText(countriesHM.get(menuItemGB.getText()));
		    System.out.println("menuItemGB");
		    player.setCountry(WorldCupUtils.UK_DESC);
		});
	}

}
