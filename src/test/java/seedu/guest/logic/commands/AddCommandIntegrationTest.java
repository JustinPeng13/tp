package seedu.guest.logic.commands;

import static seedu.guest.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.guest.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.guest.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.guest.model.Model;
import seedu.guest.model.ModelManager;
import seedu.guest.model.UserPrefs;
import seedu.guest.model.guest.Guest;
import seedu.guest.testutil.GuestBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Guest validGuest = new GuestBuilder().build();

        Model expectedModel = new ModelManager(model.getGuestBook(), new UserPrefs());
        expectedModel.addGuest(validGuest);

        assertCommandSuccess(new AddCommand(validGuest), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validGuest), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Guest guestInList = model.getGuestBook().getGuestList().get(0);
        assertCommandFailure(new AddCommand(guestInList), model, AddCommand.MESSAGE_DUPLICATE_GUEST);
    }

}
