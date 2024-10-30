/**
 * ------------------------------------------------------------------------
 * Escaping Toms basement
 * Level 3 programming project
 *
 * by Jayden Mace
 *
 * you are lost in Toms basement you need to get out before he finds you and dose unthinkable things
 *
 * you will start in a room with one door when you leave its like an escape
 * room were you need to find keys and get out before the time runs out
 * ------------------------------------------------------------------------
 */


import com.formdev.flatlaf.FlatDarkLaf
import java.awt.*
import java.awt.event.*
import javax.swing.*



//=============================================================================================




class Room(val name: String , val description: String) {
    var previous: Room? = null
    var next: Room? = null
    var locked = true

    fun addNextRoom(room: Room) {
        if (next == null) {
            next = room
            room.previous = this
        }
    }

}




/**
 * GUI class
 * Defines the UI and responds to events
 */
class GUI : JFrame(), ActionListener {


    val rooms = mutableListOf<Room>()
    var currentRoom: Room? = null
    val password = (100..999).random().toString()
    val password2 = (100..999).random().toString()



    // Setup some properties to hold the UI elements

    private lateinit var passwordTextField: JTextField
    private lateinit var currentRoomLabel: JLabel
    private lateinit var descriptionLabel: JLabel
    private lateinit var roomBack: JButton
    private lateinit var roomNext: JButton
    private lateinit var lockbox: JButton
    private lateinit var confirm: JButton


    private lateinit var doorimageIcon: ImageIcon

    /**
     * Create, build and run the UI
     */
    init {
        setupRoom()
        setupWindow()
        loadImages()
        buildUI()
        // Show the app, centred on screen
        setLocationRelativeTo(null)
        isVisible = true

        currentRoom = rooms.first()
        currentRoom!!.locked = false
        descriptionLabel.isVisible = true
        showRoom()
    }

    fun setupRoom() {
        val startRoom = Room("The Starting Room", "you wake up in a room theres a note witch says Remember: $password")
        val room2 = Room("Room 2", "not done")
        val room3 = Room("Room 3", "not done")
        val room4 = Room("Room 4", "not done")
        val room5 = Room("Room 5", "not done")
        val room6 = Room("Room 6", "not done")
        val room7 = Room("Room 7", "not done")
        val end = Room("exit", "not done")



        rooms.add(startRoom)
        rooms.add(room2)
        rooms.add(room3)
        rooms.add(room4)
        rooms.add(room5)
        rooms.add(room6)
        rooms.add(room7)
        rooms.add(end)


        startRoom.addNextRoom(room2)
        room2.addNextRoom(room3)
        room3.addNextRoom(room4)
        room4.addNextRoom(room5)
        room5.addNextRoom(room6)
        room6.addNextRoom(room7)
        room7.addNextRoom(end)

    }


    /**
     * Configure the main window
     */
    private fun setupWindow() {
        title = " Escaping Tom's Basement"
        contentPane.preferredSize = Dimension(1300, 1000)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isResizable = false
        layout = null


        pack()
    }


    private fun loadImages() {
        var doorImage = ImageIcon("images/door.png").image
        doorImage = doorImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH)
        doorimageIcon = ImageIcon(doorImage)


    }


    /**
     * Populate the UI
     */
    private fun buildUI() {
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 20)




        currentRoomLabel = JLabel("room: start", SwingConstants.CENTER)
        currentRoomLabel.bounds = Rectangle(100, 80, 400, 30)
        currentRoomLabel.font = baseFont
        add(currentRoomLabel)


        descriptionLabel = JLabel("room", SwingConstants.CENTER)
        descriptionLabel.bounds = Rectangle(100, 10, 900, 50)
        descriptionLabel.font = baseFont
        descriptionLabel.isVisible = false
        add(descriptionLabel)


        roomBack = JButton()
        roomBack.icon = doorimageIcon
        roomBack.bounds = Rectangle(520, 60, 120, 190)
        roomBack.font = baseFont
        roomBack.addActionListener(this)
        add(roomBack)


        roomNext = JButton()
        roomNext.icon = doorimageIcon
        roomNext.bounds = Rectangle(520, 500, 120, 190)
        roomNext.font = baseFont
        roomNext.addActionListener(this)
        add(roomNext)


        lockbox = JButton("$password lockbox")
        lockbox.bounds = Rectangle(50, 500, 300, 400)
        lockbox.font = baseFont
        lockbox.addActionListener(this)
        lockbox.isVisible = false
        add(lockbox)


        passwordTextField = JTextField("password", SwingConstants.CENTER)
        passwordTextField.bounds = Rectangle(900, 800, 240, 40)
        passwordTextField.font = baseFont
        passwordTextField.isVisible = false
        add(passwordTextField)


        confirm = JButton("?")
        confirm.bounds = Rectangle(1200, 800, 40, 40)
        confirm.font = baseFont
        confirm.isVisible = false
        confirm.addActionListener(this)
        add(confirm)





    }


    private fun showRoom() {
        currentRoom?.let {
            currentRoomLabel.text = "Your in: ${it.name}"
            descriptionLabel.text = " ${it.description}"


        }
        updateButtonStates()
    }


    /**
     * Handle any UI events
     */
    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {
            roomBack -> gotoPrevRoom()
            roomNext -> gotoNextRoom()
            lockbox -> showLock()
            confirm -> confirmPassword()
        }
    }


    private fun gotoPrevRoom() {
        if (currentRoom?.previous != null) {
            currentRoom = currentRoom?.previous
            descriptionLabel.isVisible = true
            showRoom()
        }
    }

    private fun gotoNextRoom() {
        if (currentRoom?.next != null) {
            currentRoom = currentRoom?.next
            descriptionLabel.isVisible = false
            showRoom()
        }
    }

    private fun showLock() {
        passwordTextField.isVisible = true
        confirm.isVisible = true
    }


    private fun confirmPassword() {

        if(passwordTextField.text == password){
            rooms[1].locked = false
            descriptionLabel.isVisible = true
            updateButtonStates()

        }
        else{
            passwordTextField.text = "incorrect"
        }
    }


    private fun updateButtonStates() {
        roomBack.isEnabled = currentRoom?.previous != null
        roomNext.isEnabled = currentRoom?.next != null && currentRoom?.locked == false

        if (currentRoom == rooms[1]) {
            lockbox.isVisible = true

        }
        else{
            lockbox.isVisible = false
            confirm.isVisible = false
            passwordTextField.isVisible = false
        }
    }
}











//=============================================================================================

/**
 * Launch the application
 */
fun main() {
    // Flat, Dark look-and-feel
    FlatDarkLaf.setup()

    // Create the UI
    GUI()
}


