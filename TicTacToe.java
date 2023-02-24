import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class TicTacToe extends JPanel
{
    JButton buttons[] = new JButton[9]; 
    int alternate = 0;//if this number is a even, then put a X. If it's odd, then put an O

    public TicTacToe()
    {
        setLayout(new GridLayout(3,3));
        initializebuttons(); 
    }

    public void initializebuttons()
    {
        Font myFont = new Font("Comic Sans", Font.BOLD, 40);
        for(int i = 0; i <= 8; i++)
        {
            buttons[i] = new JButton();
            buttons[i].setText("");
            buttons[i].setFont(myFont);
            buttons[i].addActionListener(new buttonListener());

            add(buttons[i]); //adds this button to JPanel (note: no need for JPanel.add(...)
            //because this whole class is a JPanel already          
        }
    }

    public void resetButtons()
    {
        for(int i = 0; i <= 8; i++)
        {
            buttons[i].setText("");
        }
    }

    // when a button is clicked, it generates an ActionEvent. Thus, each button needs an ActionListener. When it is clicked, it goes to this listener class that I have created and goes to the actionPerformed method. There (and in this class), we decide what we want to do.
    private class buttonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {

            JButton buttonClicked = (JButton)e.getSource(); //get the particular button that was clicked
            if(buttonClicked.getText().length() == 0)
            {
                if(alternate % 2 == 0)
                {
                    buttonClicked.setText("X");
                    buttonClicked.setForeground(new Color(241, 175, 175));
                }
                else
                {
                    buttonClicked.setText("O");
                    buttonClicked.setForeground(new Color(213, 204, 233));
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No cheating. >:(");
                alternate--;
            }
            if(checkForWin().equals("X wins!") || checkForWin().equals("O wins!"))
            {
                JOptionPane.showMessageDialog(null, "Game Over. " + checkForWin());
                resetButtons();
            }
            else if(checkForTie() == true)
            {
                JOptionPane.showMessageDialog(null, "You Tied.");
                resetButtons();
            }

            alternate++;

        }

        public String checkForWin()
        {
            /**   Reference: the button array is arranged like this as the board
             *      0 | 1 | 2
             *      3 | 4 | 5
             *      6 | 7 | 8
             */
            //horizontal win check
            if( checkAdjacent(0,1) && checkAdjacent(1,2) ) //no need to put " == true" because the default check is for true
                return buttons[0].getText() + " wins!";
            else if( checkAdjacent(3,4) && checkAdjacent(4,5) )
                return buttons[3].getText() + " wins!";
            else if ( checkAdjacent(6,7) && checkAdjacent(7,8))
                return buttons[6].getText() + " wins!";

            //vertical win check
            else if ( checkAdjacent(0,3) && checkAdjacent(3,6))
                return buttons[0].getText() + " wins!";  
            else if ( checkAdjacent(1,4) && checkAdjacent(4,7))
                return buttons[1].getText() + " wins!";
            else if ( checkAdjacent(2,5) && checkAdjacent(5,8))
                return buttons[2].getText() + " wins!";

            //diagonal win check
            else if ( checkAdjacent(0,4) && checkAdjacent(4,8))
                return buttons[0].getText() + " wins!";  
            else if ( checkAdjacent(2,4) && checkAdjacent(4,6))
                return buttons[2].getText() + " wins!";
            else 
                return "";
        }

        public boolean checkForTie()
        {
            for (int i = 0; i < buttons.length; i++)
            {
                if (buttons[i].getText().equals(""))
                {
                    return false;
                }
            }
            return true;
        }

        public boolean checkAdjacent(int a, int b)
        {
            if ( buttons[a].getText().equals(buttons[b].getText()) && !buttons[a].getText().equals("") )
                return true;
            else
                return false;
        }
    }

    public static void main(String[] args) 
    {
        JFrame window = new JFrame("Tic-Tac-Toe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new TicTacToe());
        window.setBounds(300,200,300,300);
        window.setVisible(true);
    }
}
