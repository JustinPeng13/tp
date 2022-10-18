package seedu.guest.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.guest.model.guest.Guest;

/**
 * An UI component that displays information of a {@code Guest}.
 */
public class GuestCard extends UiPart<Region> {

    private static final String FXML = "GuestListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Guest guest;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label room;
    @FXML
    private Label dateRange;
    @FXML
    private Label numberOfGuests;
    @FXML
    private Label isRoomClean;
    @FXML
    private Label bill;

    /**
     * Creates a {@code GuestCode} with the given {@code Guest} and index to display.
     */
    public GuestCard(Guest guest, int displayedIndex) {
        super(FXML);
        this.guest = guest;
        id.setText(displayedIndex + ". ");
        name.setText(guest.getName().fullName);
        name.setWrapText(true);

        phone.setText(guest.getPhone().value);
        phone.setWrapText(true);

        email.setText(guest.getEmail().value);
        email.setWrapText(true);

        room.setText(guest.getRoom().value);
        room.setWrapText(true);

        dateRange.setText(guest.getDateRange().value);
        dateRange.setWrapText(true);

        numberOfGuests.setText("No. of Guests: " + guest.getNumberOfGuests().value);

        isRoomClean.setText("Room Cleaned: ");

        // Set graphic based on status of isRoomClean
        if (guest.getIsRoomClean().value.equals("yes")) {
            Image image = new Image(getClass().getResourceAsStream("/images/roomClean.png"));
            ImageView setImage = new ImageView(image);
            setImage.setFitWidth(18);
            setImage.setFitHeight(18);
            isRoomClean.setGraphic(setImage);
        } else {
            Image image = new Image(getClass().getResourceAsStream("/images/roomNotClean.png"));
            ImageView setImage = new ImageView(image);
            setImage.setFitWidth(18);
            setImage.setFitHeight(18);
            isRoomClean.setGraphic(setImage);
        }

        isRoomClean.setContentDisplay(ContentDisplay.RIGHT);

        bill.setText("Bill: " + guest.getBill().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GuestCard)) {
            return false;
        }

        // state check
        GuestCard card = (GuestCard) other;
        return id.getText().equals(card.id.getText())
                && guest.equals(card.guest);
    }
}
