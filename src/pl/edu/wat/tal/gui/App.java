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

import de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel;

import java.awt.Component;
import java.text.ParseException;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import pl.edu.wat.tal.messages.Messages;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JButton;

public class App
{

    private JFrame frame;
    private JTextField textField;
    private Messages singletonMessages;
    private JTextField textField_1;

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

        try
        {
            UIManager.setLookAndFeel( new SyntheticaClassyLookAndFeel() );
        }
        catch ( UnsupportedLookAndFeelException | ParseException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        frame = new JFrame("FVS ALGORITHM GURNIAK JEDYNAK");
        frame.setBounds( 100, 100, 815, 498 );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.getContentPane().setLayout( new GridLayout( 1, 0, 0, 0 ) );

        JPanel panel = new JPanel();
        frame.getContentPane().add( panel );
        panel.setLayout( new GridLayout( 1, 0, 0, 0 ) );

        JTextArea textArea = new JTextArea();
        //panel.add( textArea );
        
        //textArea.setEditable( false );
        JScrollPane scroll = new JScrollPane( textArea );
        scroll.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        panel.add(scroll);

        JPanel panel_1 = new JPanel();
        panel.add( panel_1 );
        panel_1.setLayout( new GridLayout( 3, 1 ) );

        JPanel panel_3 = new JPanel();
        panel_1.add( panel_3 );
        panel_3.setLayout( new GridLayout( 2, 1 ) );

        JPanel panel_3a = new JPanel();
        panel_3a.setLayout( new GridLayout( 3, 1 ) );
        panel_3.add( panel_3a );

        JLabel lblNewLabel = new JLabel( "Wybór algorytmu" );
        lblNewLabel.setFont( new Font( "Tahoma", Font.BOLD, 11 ) );
        lblNewLabel.setHorizontalAlignment( SwingConstants.CENTER );
        panel_3a.add( lblNewLabel );

        JCheckBox chckbxNewCheckBox = new JCheckBox( "Algorytm Brute-force" );
        chckbxNewCheckBox.setSelected( true );
        panel_3a.add( chckbxNewCheckBox );

        JCheckBox chckbxNewCheckBox_1 = new JCheckBox( "Algorytm warstwowy" );
        chckbxNewCheckBox_1.setSelected( true );
        panel_3a.add( chckbxNewCheckBox_1 );

        JPanel panel_3b = new JPanel();
        panel_3.add( panel_3b );
        panel_3b.setLayout( new GridLayout( 3, 1 ) );

        JLabel lblNewLabel_1 = new JLabel( "Typ pomiaru" );
        lblNewLabel_1.setFont( new Font( "Tahoma", Font.BOLD, 11 ) );
        lblNewLabel_1.setHorizontalAlignment( SwingConstants.CENTER );
        panel_3b.add( lblNewLabel_1 );

        JCheckBox chckbxNewCheckBox_2 = new JCheckBox( "Czas wykonania" );
        chckbxNewCheckBox_2.setSelected( true );
        panel_3b.add( chckbxNewCheckBox_2 );

        JCheckBox chckbxNewCheckBox_3 = new JCheckBox( "Z³o¿onoœæ pamiêciowa" );
        chckbxNewCheckBox_3.setEnabled( false );
        panel_3b.add( chckbxNewCheckBox_3 );

        JPanel panel_10 = new JPanel();
        panel_10.setLayout( new GridLayout( 4, 1 ) );
        panel_1.add( panel_10 );

        JPanel panel_5 = new JPanel();
        panel_10.add( panel_5 );
        panel_5.setBounds( 0, 2, 392, 45 );

        JLabel lblNewLabel_2 = new JLabel( "Ustawienia generatora" );
        panel_5.add( lblNewLabel_2 );
        lblNewLabel_2.setHorizontalAlignment( SwingConstants.CENTER );
        lblNewLabel_2.setFont( new Font( "Tahoma", Font.BOLD, 11 ) );

        JPanel panel_7 = new JPanel();
        panel_10.add( panel_7 );
        panel_7.setBounds( 0, 137, 392, 45 );
        panel_7.setLayout( null );

        JLabel label_1 = new JLabel( "Liczba generowanych grafów" );
        label_1.setBounds( 206, 8, 183, 14 );
        panel_7.add( label_1 );

        textField_1 = new JTextField();
        textField_1.setColumns( 10 );
        textField_1.setBounds( 10, 8, 48, 20 );
        panel_7.add( textField_1 );

        JPanel panel_8 = new JPanel();
        panel_10.add( panel_8 );
        panel_8.setLayout( new GridLayout( 1, 2 ) );

        JRadioButton rdbtnNewRadioButton = new JRadioButton( "wagi losowe" );
        panel_8.add( rdbtnNewRadioButton );
        rdbtnNewRadioButton.setSelected( true );
        rdbtnNewRadioButton.setHorizontalAlignment( SwingConstants.LEFT );

        JRadioButton rdbtnNewRadioButton_1 = new JRadioButton( "wagi sta³e równe 1" );
        panel_8.add( rdbtnNewRadioButton_1 );
        rdbtnNewRadioButton_1.setHorizontalAlignment( SwingConstants.LEFT );

        ButtonGroup btButtonGroup = new ButtonGroup();
        btButtonGroup.add( rdbtnNewRadioButton );
        btButtonGroup.add( rdbtnNewRadioButton_1 );
        
        JPanel panel_9 = new JPanel();
        panel_10.add( panel_9 );
        panel_9.setLayout( new GridLayout( 2, 1 ) );

        JLabel lblNewLabel_4 = new JLabel( "Gêstoœæ grafu" );
        lblNewLabel_4.setHorizontalAlignment( SwingConstants.CENTER );
        panel_9.add( lblNewLabel_4 );

        JSlider slider = new JSlider();
        slider.setToolTipText("Ustaw g\u0119sto\u015B\u0107 grafu z zakresu 0,01 - 1,00");
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMinorTickSpacing(1);
        slider.setValue( 0 );
        panel_9.add( slider );

        JPanel panel_2 = new JPanel();
        panel_1.add( panel_2 );
        panel_2.setLayout( new GridLayout( 4, 1 ) );

        JPanel panel_4 = new JPanel();
        panel_2.add( panel_4 );
        panel_4.setBounds( 0, 92, 392, 45 );
        panel_4.setLayout( null );

        JLabel label = new JLabel( "Zakres wierzcho³ków w serii" );
        label.setBounds( 202, 14, 187, 14 );
        panel_4.add( label );

        JSpinner spinner = new JSpinner();
        spinner.setModel( new SpinnerNumberModel( 3, 3, 16, 1 ) );
        spinner.setBounds( 36, 11, 39, 20 );
        panel_4.add( spinner );

        JSpinner spinner_1 = new JSpinner();
        spinner_1.setModel( new SpinnerNumberModel( 16, 3, 16, 1 ) );
        spinner_1.setBounds( 119, 11, 39, 20 );
        panel_4.add( spinner_1 );

        JLabel lblOd = new JLabel( "od" );
        lblOd.setBounds( 10, 14, 16, 14 );
        panel_4.add( lblOd );

        JLabel lblDo = new JLabel( "do" );
        lblDo.setBounds( 93, 14, 16, 14 );
        panel_4.add( lblDo );

        JPanel panel_6 = new JPanel();
        panel_6.setBounds( 0, 47, 392, 45 );
        panel_2.add( panel_6 );
        panel_6.setLayout( null );

        textField = new JTextField();
        textField.setBounds( 10, 11, 48, 20 );
        panel_6.add( textField );
        textField.setColumns( 10 );

        JLabel lblNewLabel_3 = new JLabel( "Iloœæ pomiarów dla zadania" );
        lblNewLabel_3.setBounds( 204, 14, 185, 14 );
        panel_6.add( lblNewLabel_3 );

        JPanel panel_11 = new JPanel();
        panel_2.add( panel_11 );

        JLabel lblRozpocznijSymulacj = new JLabel( "Rozpocznij symulacjê" );
        lblRozpocznijSymulacj.setFont( new Font( "Tahoma", Font.BOLD, 11 ) );
        panel_11.add( lblRozpocznijSymulacj );

        JPanel panel_12 = new JPanel();
        panel_2.add( panel_12 );

        JButton btnStart = new JButton( "Start" );
        btnStart.setForeground( new Color( 0, 0, 0 ) );
        panel_12.add( btnStart );



    }
}
