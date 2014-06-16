package pl.edu.wat.tal.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.DropMode;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class App
{

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    /**
     * Launch the application.
     */
    public static void main( String[] args )
    {
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                try
                {
                    App window = new App();
                    window.frame.setVisible( true );
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
            }
        } );
    }

    /**
     * Create the application.
     */
    public App()
    {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        frame = new JFrame();
        frame.setBounds( 100, 100, 800, 498 );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.getContentPane().setLayout( new GridLayout( 1, 0, 0, 0 ) );

        JPanel panel = new JPanel();
        frame.getContentPane().add( panel );
        panel.setLayout( new GridLayout( 1, 0, 0, 0 ) );

        JTextArea textArea = new JTextArea();
        panel.add( textArea );

        JPanel panel_1 = new JPanel();
        panel.add( panel_1 );
        panel_1.setLayout( new GridLayout( 2, 1 ) );

        JPanel panel_3 = new JPanel();
        panel_1.add( panel_3 );
        panel_3.setLayout( new GridLayout( 2, 1 ) );
        
        JPanel panel_3a = new JPanel( );
        panel_3a.setLayout( new GridLayout( 3, 1 ) );
        panel_3.add( panel_3a );

        JLabel lblNewLabel = new JLabel( "Wybór algorytmu" );
        lblNewLabel.setFont( new Font( "Tahoma", Font.BOLD, 11 ) );
        lblNewLabel.setHorizontalAlignment( SwingConstants.CENTER );
        panel_3a.add( lblNewLabel );

        JCheckBox chckbxNewCheckBox = new JCheckBox( "Algorytm Brute-force" );
        panel_3a.add( chckbxNewCheckBox );

        JCheckBox chckbxNewCheckBox_1 = new JCheckBox( "Algorytm warstwowy" );
        panel_3a.add( chckbxNewCheckBox_1 );

        JPanel panel_3b = new JPanel();
        panel_3.add( panel_3b );
        panel_3b.setLayout( new GridLayout( 3, 1 ) );

        JLabel lblNewLabel_1 = new JLabel( "Typ pomiaru" );
        lblNewLabel_1.setFont( new Font( "Tahoma", Font.BOLD, 11 ) );
        lblNewLabel_1.setHorizontalAlignment( SwingConstants.CENTER );
        panel_3b.add( lblNewLabel_1 );

        JCheckBox chckbxNewCheckBox_2 = new JCheckBox( "Czas wykonania" );
        panel_3b.add( chckbxNewCheckBox_2 );

        JCheckBox chckbxNewCheckBox_3 = new JCheckBox( "Z³o¿onoœæ pamiêciowa" );
        panel_3b.add( chckbxNewCheckBox_3 );

        JPanel panel_2 = new JPanel();
        panel_1.add( panel_2 );
        panel_2.setLayout(null);
        
        JPanel panel_5 = new JPanel();
        panel_5.setBounds(0, 2, 392, 45);
        panel_2.add(panel_5);
        
        JLabel lblNewLabel_2 = new JLabel("Ustawienia generatora");
        panel_5.add(lblNewLabel_2);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
        
        JPanel panel_6 = new JPanel();
        panel_6.setBounds(0, 47, 392, 45);
        panel_2.add(panel_6);
        panel_6.setLayout(null);
        
        textField = new JTextField();
        textField.setBounds(10, 11, 109, 20);
        panel_6.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Iloœæ powtórzeñ jednego zadania");
        lblNewLabel_3.setBounds(141, 14, 208, 14);
        panel_6.add(lblNewLabel_3);
        
        JPanel panel_4 = new JPanel();
        panel_4.setBounds(0, 92, 392, 45);
        panel_2.add(panel_4);
        panel_4.setLayout(null);
        
        JLabel label = new JLabel("Zakres wierzcho³ków w serii");
        label.setBounds(141, 14, 208, 14);
        panel_4.add(label);
        
        JSpinner spinner = new JSpinner();
        spinner.setModel(new SpinnerNumberModel(3, 3, 16, 1));
        spinner.setBounds(27, 11, 33, 20);
        panel_4.add(spinner);
        
        JSpinner spinner_1 = new JSpinner();
        spinner_1.setModel(new SpinnerNumberModel(16, 3, 16, 1));
        spinner_1.setBounds(79, 11, 39, 20);
        panel_4.add(spinner_1);
        
        JLabel lblOd = new JLabel("od:");
        lblOd.setBounds(10, 14, 16, 14);
        panel_4.add(lblOd);
        
        JLabel lblDo = new JLabel("do:");
        lblDo.setBounds(63, 14, 16, 14);
        panel_4.add(lblDo);
        
        JPanel panel_7 = new JPanel();
        panel_7.setBounds(0, 137, 392, 45);
        panel_2.add(panel_7);
        panel_7.setLayout(null);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(10, 11, 109, 20);
        panel_7.add(textField_1);
        
        JLabel label_1 = new JLabel("Iloœæ spójnych sk³adowych");
        label_1.setBounds(142, 14, 208, 14);
        panel_7.add(label_1);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(10, 198, 109, 20);
        panel_2.add(textField_2);
        
        JLabel label_2 = new JLabel("Iloœæ wierzcho³ków");
        label_2.setBounds(143, 201, 208, 14);
        panel_2.add(label_2);

    }
}
