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


    private val rooms = mutableListOf<Room>()
    private var currentRoom: Room? = null

    private var clicked3 = false
    private var clicked6 = false
    private var clicked5 = false
    private var clicks = 0
    private var ciphers = false





    // Setup some properties to hold the UI elements

    private lateinit var passwordTextField: JTextField
    private lateinit var currentRoomLabel: JLabel
    private lateinit var descriptionLabel: JLabel
    private lateinit var roomBack: JButton
    private lateinit var roomNext: JButton
    private lateinit var lockBox: JButton
    private lateinit var confirm: JButton
    private lateinit var number1: JButton
    private lateinit var number2: JButton
    private lateinit var number3: JButton
    private lateinit var number4: JButton
    private lateinit var number5: JButton
    private lateinit var number6: JButton
    private lateinit var number7: JButton
    private lateinit var number8: JButton
    private lateinit var number9: JButton
    private lateinit var number0: JButton
    private lateinit var keyPad: JButton
    private lateinit var guess1: JLabel
    private lateinit var guess2: JLabel
    private lateinit var guess3: JLabel
    private lateinit var skipNext: JButton
    private lateinit var openWordSearch: JButton
    private lateinit var WordSearch1: JLabel
    private lateinit var WordSearch2: JLabel
    private lateinit var WordSearch3: JLabel
    private lateinit var WordSearch4: JLabel
    private lateinit var WordSearch5: JLabel
    private lateinit var WordSearch6: JLabel
    private lateinit var WordSearch7: JLabel
    private lateinit var WordSearch8: JLabel
    private lateinit var WordSearch9: JLabel

    private lateinit var openCipher: JButton
    private lateinit var normalTextLabel: JLabel
    private lateinit var ciphertextLabel: JLabel





    private lateinit var doorimageIcon: ImageIcon
    private lateinit var lockboxIcon: ImageIcon

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
        val startRoom = Room("The Starting Room", "you wake up in a room theres a note witch says Remember: 738843")
        val room2 = Room("Room 2", "in the lock-box theres a key and a note witch says 3 6 5")
        val room3 = Room("Room 3", "")
        val room4 = Room("Room 4", "you find same paper with a note saying no capitals and it starts with E")
        val room5 = Room("Room 5", "theres a TV that says I love caesar ciphers try A - J with    vh kjbvnwc")
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

        var lockboxImage = ImageIcon("images/lockbox.png").image
        lockboxImage = lockboxImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH)
        lockboxIcon = ImageIcon(lockboxImage)

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


        lockBox = JButton()
        lockBox.icon = lockboxIcon
        lockBox.bounds = Rectangle(50, 500, 300, 400)
        lockBox.font = baseFont
        lockBox.addActionListener(this)
        lockBox.isVisible = false
        add(lockBox)


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





        number1 = JButton("1")
        number1.bounds = Rectangle(1000 , 400, 40, 40)
        number1.font = baseFont
        number1.isVisible = false
        number1.addActionListener(this)
        add(number1)

        number2 = JButton("2")
        number2.bounds = Rectangle(1100, 400, 40, 40)
        number2.font = baseFont
        number2.isVisible = false
        number2.addActionListener(this)
        add(number2)

        number3 = JButton("3")
        number3.bounds = Rectangle(1200, 400, 40, 40)
        number3.font = baseFont
        number3.isVisible = false
        number3.addActionListener(this)
        add(number3)

        number4 = JButton("4")
        number4.bounds = Rectangle(1000, 500, 40, 40)
        number4.font = baseFont
        number4.isVisible = false
        number4.addActionListener(this)
        add(number4)

        number5 = JButton("5")
        number5.bounds = Rectangle(1100, 500, 40, 40)
        number5.font = baseFont
        number5.isVisible = false
        number5.addActionListener(this)
        add(number5)

        number6 = JButton("6")
        number6.bounds = Rectangle(1200, 500, 40, 40)
        number6.font = baseFont
        number6.isVisible = false
        number6.addActionListener(this)
        add(number6)

        number7 = JButton("7")
        number7.bounds = Rectangle(1000, 600, 40, 40)
        number7.font = baseFont
        number7.isVisible = false
        number7.addActionListener(this)
        add(number7)

        number8 = JButton("8")
        number8.bounds = Rectangle(1100, 600, 40, 40)
        number8.font = baseFont
        number8.isVisible = false
        number8.addActionListener(this)
        add(number8)

        number9 = JButton("9")
        number9.bounds = Rectangle(1200, 600, 40, 40)
        number9.font = baseFont
        number9.isVisible = false
        number9.addActionListener(this)
        add(number9)

        number0 = JButton("0")
        number0.bounds = Rectangle(1100, 700, 40, 40)
        number0.font = baseFont
        number0.isVisible = false
        number0.addActionListener(this)
        add(number0)




        keyPad = JButton("░")
        keyPad.bounds = Rectangle(50, 200, 50, 60)
        keyPad.font = baseFont
        keyPad.addActionListener(this)
        keyPad.isVisible = false
        add(keyPad)


        guess1 = JLabel("██", SwingConstants.CENTER)
        guess1.bounds = Rectangle(1000, 300, 50, 50)
        guess1.font = baseFont
        guess1.isVisible = false
        add(guess1)

        guess2 = JLabel("██", SwingConstants.CENTER)
        guess2.bounds = Rectangle(1100, 300, 50, 50)
        guess2.font = baseFont
        guess2.isVisible = false
        add(guess2)

        guess3 = JLabel("██", SwingConstants.CENTER)
        guess3.bounds = Rectangle(1200, 300, 50, 50)
        guess3.font = baseFont
        guess3.isVisible = false
        add(guess3)

        skipNext = JButton("next")
        skipNext.bounds = Rectangle(1100, 50, 80, 50)
        skipNext.font = baseFont
        skipNext.addActionListener(this)
        skipNext.isVisible = true
        add(skipNext)




        openWordSearch = JButton("░")
        openWordSearch.bounds = Rectangle(50, 200, 50, 60)
        openWordSearch.font = baseFont
        openWordSearch.addActionListener(this)
        openWordSearch.isVisible = false
        add(openWordSearch)




        WordSearch1 = JLabel("W  W  E  G  H  I  P  L  B", SwingConstants.CENTER)
        WordSearch1.bounds = Rectangle(1000, 420, 240, 30)
        WordSearch1.font = baseFont
        WordSearch1.isVisible = false
        add(WordSearch1)

        WordSearch2 = JLabel("W  W  L  S  N  M  K  L  Q", SwingConstants.CENTER)
        WordSearch2.bounds = Rectangle(1000, 440, 240, 30)
        WordSearch2.font = baseFont
        WordSearch2.isVisible = false
        add(WordSearch2)

        WordSearch3 = JLabel("I  L  A  S  C B  E  U  I", SwingConstants.CENTER)
        WordSearch3.bounds = Rectangle(1000, 460, 240, 30)
        WordSearch3.font = baseFont
        WordSearch3.isVisible = false
        add(WordSearch3)

        WordSearch4 = JLabel("Z  J  A  Y  E  A  K  P  E", SwingConstants.CENTER)
        WordSearch4.bounds = Rectangle(1000, 480, 240, 30)
        WordSearch4.font = baseFont
        WordSearch4.isVisible = false
        add(WordSearch4)

        WordSearch5 = JLabel("A  B  E  L  U  I  P  X  T", SwingConstants.CENTER)
        WordSearch5.bounds = Rectangle(1000, 500, 240, 30)
        WordSearch5.font = baseFont
        WordSearch5.isVisible = false
        add(WordSearch5)

        WordSearch6 = JLabel("L  Q  E  B  C  V  N  E  M", SwingConstants.CENTER)
        WordSearch6.bounds = Rectangle(1000, 520, 240, 30)
        WordSearch6.font = baseFont
        WordSearch6.isVisible = false
        add(WordSearch6)

        WordSearch7 = JLabel("O  J  Y  E  P  A  C  S  U", SwingConstants.CENTER)
        WordSearch7.bounds = Rectangle(1000, 540, 240, 30)
        WordSearch7.font = baseFont
        WordSearch7.isVisible = false
        add(WordSearch7)

        WordSearch8 = JLabel("U  I  N  R  Y  D  J  J  L", SwingConstants.CENTER)
        WordSearch8.bounds = Rectangle(1000, 560, 240, 30)
        WordSearch8.font = baseFont
        WordSearch8.isVisible = false
        add(WordSearch8)

        WordSearch9 = JLabel("I  U  O  E  P  A  C  S  E", SwingConstants.CENTER)
        WordSearch9.bounds = Rectangle(1000, 580, 240, 30)
        WordSearch9.font = baseFont
        WordSearch9.isVisible = false
        add(WordSearch9)



        openCipher = JButton("░")
        openCipher.bounds = Rectangle(50, 200, 50, 60)
        openCipher.font = baseFont
        openCipher.addActionListener(this)
        openCipher.isVisible = false
        add(openCipher)


        normalTextLabel = JLabel("A B C D E  F G H  I  J K L M N O P Q R S T U V W X Y Z", SwingConstants.LEFT)
        normalTextLabel.bounds = Rectangle(700, 560, 700, 30)
        normalTextLabel.font = baseFont
        normalTextLabel.isVisible = false
        add(normalTextLabel)

        ciphertextLabel = JLabel("J  K L M N O P Q R S T U V W X Y Z A B  C D E F G H  I", SwingConstants.LEFT)
        ciphertextLabel.bounds = Rectangle(700, 580, 700, 30)
        ciphertextLabel.font = baseFont
        ciphertextLabel.isVisible = false
        add(ciphertextLabel)


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
            skipNext -> unlockRoom()
            roomBack -> gotoPrevRoom()
            roomNext -> gotoNextRoom()
            lockBox  -> showLock()
            confirm  -> confirmPassword()
            keyPad   -> showKeys()

            number3  -> checkCode3()
            number6  -> checkCode6()
            number5  -> checkCode5()
            number1  -> checkCodeWrong()
            number2  -> checkCodeWrong()
            number4  -> checkCodeWrong()
            number7  -> checkCodeWrong()
            number8  -> checkCodeWrong()
            number9  -> checkCodeWrong()
            number0  -> checkCodeWrong()

            openWordSearch -> showWordSearch()
            openCipher -> showCipherInput()


        }
    }


    private fun unlockRoom() {
        currentRoom?.locked = false
        updateButtonStates()
    }



    private fun gotoPrevRoom() {
        if (currentRoom?.previous != null) {
            currentRoom = currentRoom?.previous
            descriptionLabel.isVisible = true
            hideLastRoom()
            showRoom()
        }
    }

    private fun gotoNextRoom() {
        if (currentRoom?.next != null) {
            currentRoom = currentRoom?.next
            descriptionLabel.isVisible = false
            hideLastRoom()
            showRoom()
        }
    }

    private fun showLock() {
        passwordTextField.isVisible = true
        confirm.isVisible = true
    }


    private fun confirmPassword() {

        if(currentRoom == rooms[1]) {
            if (passwordTextField.text == "738843") {
                rooms[1].locked = false
                descriptionLabel.isVisible = true
                updateButtonStates()

            } else {
                passwordTextField.text = "Incorrect"
            }
        }

        if(currentRoom == rooms[3]) {
                if (passwordTextField.text == "escape") {
                    rooms[3].locked = false

                    updateButtonStates()
                } else {
                    passwordTextField.text = "incorrect"
                }
        }

        if(currentRoom == rooms[4]) {
                if (passwordTextField.text == "escape") {
                    descriptionLabel.isVisible = true
                    rooms[3].locked = false
                    descriptionLabel.text = " The TV changes to good job the door is open but you should now try a Vigenère cipher with the key being escape on azgn nsy ygt is xzg lpwx jqob csm peth hg qui mr gpe ias xkvt xljge cmrw"

                    updateButtonStates()
                }
                else{
                    passwordTextField.text = "Incorrect"
                }
        }
    }


    private fun hideLastRoom(){
        keyPad.isVisible = false
        number1.isVisible = false
        number2.isVisible = false
        number3.isVisible = false
        number4.isVisible = false
        number5.isVisible = false
        number6.isVisible = false
        number7.isVisible = false
        number8.isVisible = false
        number9.isVisible = false
        number0.isVisible = false

        guess1.isVisible =  false
        guess2.isVisible = false
        guess3.isVisible = false

        confirm.isVisible = false
        passwordTextField.isVisible = false

        openWordSearch.isVisible = false
        WordSearch1.isVisible = false
        WordSearch2.isVisible = false
        WordSearch3.isVisible = false
        WordSearch4.isVisible = false
        WordSearch5.isVisible = false
        WordSearch6.isVisible = false
        WordSearch7.isVisible = false
        WordSearch8.isVisible = false
        WordSearch9.isVisible = false

    }


    private fun showKeys(){
        number1.isVisible = true
        number2.isVisible = true
        number3.isVisible = true
        number4.isVisible = true
        number5.isVisible = true
        number6.isVisible = true
        number7.isVisible = true
        number8.isVisible = true
        number9.isVisible = true
        number0.isVisible = true


    }

    private fun checkCode3(){
        clicked3 = true
        clicks ++
        checkCode()
        updateButtonStates()
    }
    private fun checkCode6(){
        clicked6 = true
        clicks ++
        checkCode()
        updateButtonStates()
    }
    private fun checkCode5(){
        clicked5 = true
        clicks ++
        checkCode()
        updateButtonStates()
    }



    private fun checkCodeWrong(){
        clicks ++
        checkCode()
        updateButtonStates()
    }



    private fun checkCode(){

        if(clicks == 1){
            guess1.isVisible = true
        }
        if(clicks == 2){
            guess2.isVisible = true
        }
        if(clicks == 3) {
            guess3.isVisible = true

            if (clicked3 && clicked6 && clicked5) {
                rooms[2].locked = false

                updateButtonStates()
            }
            else{
                clicked5 = false
                clicked5 = false
                clicked5 = false
                clicks = 0
                guess1.isVisible = false
                guess2.isVisible = false
                guess3.isVisible = false

                updateButtonStates()
            }
        }
    }



    private fun showWordSearch(){
        WordSearch1.isVisible = true
        WordSearch2.isVisible = true
        WordSearch3.isVisible = true
        WordSearch4.isVisible = true
        WordSearch5.isVisible = true
        WordSearch6.isVisible = true
        WordSearch7.isVisible = true
        WordSearch8.isVisible = true
        WordSearch9.isVisible = true

        passwordTextField.isVisible = true
        confirm.isVisible = true

        updateButtonStates()
    }


    private fun showCipherInput(){
        passwordTextField.isVisible = true
        confirm.isVisible = true
        ciphertextLabel.isVisible = true
        normalTextLabel.isVisible = true
    }









    private fun updateButtonStates() {
        roomBack.isEnabled = currentRoom?.previous != null
        roomNext.isEnabled = currentRoom?.next != null && currentRoom?.locked == false

        if (currentRoom == rooms[1]) {
            lockBox.isVisible = true

        }
        else{
            lockBox.isVisible = false


        }
        if (currentRoom == rooms[2]) {
            keyPad.isVisible = true
        }

        if(currentRoom == rooms[3]) {
            openWordSearch.isVisible = true
            descriptionLabel.isVisible = true

        }
        if (currentRoom == rooms[4]){
            descriptionLabel.isVisible = true
            openCipher.isVisible = true
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


