import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

// Enum for moves
enum Move {
    ROCK, PAPER, SCISSORS, LIZARD, SPOCK
}

// Class representing a player
class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }
}

// Class representing the game
class SPSLS extends JFrame implements ActionListener {
    private JButton rockButton;
    private JButton paperButton;
    private JButton scissorsButton;
    private JButton lizardButton;
    private JButton spockButton;
    private JLabel resultLabel;
    private JLabel scoreLabel;
    private Player player;
    private Player computer;
    private Random random;

    public SPSLS() {
        setTitle("Rock, Paper, Scissors");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JPanel buttonPanel = new JPanel();
        rockButton = new JButton("Rock");
        paperButton = new JButton("Paper");
        scissorsButton = new JButton("Scissors");
        lizardButton = new JButton("Lizard");
        spockButton = new JButton("Spock");
        rockButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);
        lizardButton.addActionListener(this);
        spockButton.addActionListener(this);
        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(lizardButton);
        buttonPanel.add(spockButton);
        add(buttonPanel);

        resultLabel = new JLabel("Make your move!", SwingConstants.CENTER);
        add(resultLabel);

        player = new Player("You");
        computer = new Player("Computer");
        scoreLabel = new JLabel("Score - You: 0 Computer: 0", SwingConstants.CENTER);
        add(scoreLabel);

        random = new Random();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userMove = e.getActionCommand();
        Move computerMove = getComputerMove();
        Move userMoveEnum = Move.valueOf(userMove.toUpperCase());

        String result = determineWinner(userMoveEnum, computerMove);
        resultLabel.setText("You: " + userMove + " | Computer: " + computerMove + " | " + result);
        scoreLabel.setText("Score - You: " + player.getScore() + " Computer: " + computer.getScore());
    }

    private Move getComputerMove() {
        return Move.values()[random.nextInt(Move.values().length)];
    }

    private String determineWinner(Move userMove, Move computerMove) {
        if (userMove == computerMove) {
            return "It's a tie!";
        }

        switch (userMove) {
            case ROCK:
                return (computerMove == Move.SCISSORS || computerMove == Move.LIZARD) ? handleWin() : handleLoss();
            case PAPER:
                return (computerMove == Move.ROCK || computerMove == Move.SPOCK) ? handleWin() : handleLoss();
            case SCISSORS:
                return (computerMove == Move.PAPER || computerMove == Move.LIZARD) ? handleWin() : handleLoss();
            case LIZARD:
                return (computerMove == Move.SPOCK || computerMove == Move.PAPER) ? handleWin() : handleLoss();
            case SPOCK:
                return (computerMove == Move.SCISSORS || computerMove == Move.ROCK) ? handleWin() : handleLoss();
            default:
                return "Invalid move!";
        }
    }

    private String handleWin() {
        player.incrementScore();
        return "You win!";
    }

    private String handleLoss() {
        computer.incrementScore();
        return "You lose!";
    }

    public static void main(String[] args) {
        new SPSLS();
    }
}
