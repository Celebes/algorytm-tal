package pl.edu.wat.tal.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JTextArea;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.TextArea;

import javax.swing.DropMode;

import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel;

import java.awt.Component;
import java.net.Inet4Address;
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

import pl.edu.wat.tal.helper.CommonVariables;
import pl.edu.wat.tal.messages.Messages;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import org.junit.internal.runners.statements.RunAfters;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class App
{

    private JFrame frame;
    private JTextField tfNumberOfMeasurements;
    private Messages singletonMessages;
    private JTextField tfNumberOfGraphs;
    private JTextArea textArea;
    private JCheckBox chkBoxBruteForceAlgorithm;
    private JCheckBox chkBoxLayerAlgorithm;
    private JCheckBox chkBoxComputeComplexity;
    private JSpinner spinnerVertexFrom;
    private JSpinner spinnerVertextTo;
    private JRadioButton rbRandomWeight;
    private JRadioButton rbStateWeight;
    private JSlider sliderDensityGraph;
    private JCheckBox chkBoxComputeMemory;

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
                    runAlgorithms();
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
        frame = new JFrame( "FVS ALGORITHM GURNIAK JEDYNAK" );
        frame.setBounds( 100, 100, 815, 498 );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.getContentPane().setLayout( new GridLayout( 1, 0, 0, 0 ) );

        JPanel panel = new JPanel();
        frame.getContentPane().add( panel );
        panel.setLayout( new GridLayout( 1, 0, 0, 0 ) );

        textArea = new JTextArea();
        // panel.add( textArea );

        // textArea.setEditable( false );
        JScrollPane scroll = new JScrollPane( textArea );
        scroll.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        panel.add( scroll );

        JPanel panel_1 = new JPanel();
        panel.add( panel_1 );
        panel_1.setLayout( new GridLayout( 3, 1 ) );

        JPanel panel_3 = new JPanel();
        panel_1.add( panel_3 );
        panel_3.setLayout( new GridLayout( 2, 1 ) );

        JPanel panel_3a = new JPanel();
        panel_3a.setLayout( new GridLayout( 3, 1 ) );
        panel_3.add( panel_3a );

        JLabel lbChooseAlgorithm = new JLabel( "Wyb�r algorytmu" );
        lbChooseAlgorithm.setFont( new Font( "Tahoma", Font.BOLD, 11 ) );
        lbChooseAlgorithm.setHorizontalAlignment( SwingConstants.CENTER );
        panel_3a.add( lbChooseAlgorithm );

        chkBoxBruteForceAlgorithm = new JCheckBox( "Algorytm Brute-force" );
        chkBoxBruteForceAlgorithm.addItemListener( new ItemListener()
        {
            public void itemStateChanged( ItemEvent arg0 )
            {
                if ( chkBoxBruteForceAlgorithm.isSelected() )
                {
                    textArea.setText( "Wybrano algorytm Brute Force" );
                    CommonVariables.getInstance().GENERUJ_ALGORYTM_BRUTE_FORCE = true;
                }
                else
                {
                    textArea.setText( "Nie wybrano algorytm Brute Force" );
                    CommonVariables.getInstance().GENERUJ_ALGORYTM_BRUTE_FORCE = false;
                }
            }
        } );

        /*        chkBoxBruteForceAlgorithm.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent arg0) {
                        if(chkBoxBruteForceAlgorithm.isSelected())
                        {
                            textArea.setText( "Wybrano algorytm Brute Force" );
                        }else
                        {
                            textArea.setText( "Nie wybrano algorytm Brute Force" );
                        }
                    }
                });*/
        chkBoxBruteForceAlgorithm.setSelected( true );
        panel_3a.add( chkBoxBruteForceAlgorithm );

        chkBoxLayerAlgorithm = new JCheckBox( "Algorytm warstwowy" );
        chkBoxLayerAlgorithm.addItemListener( new ItemListener()
        {
            public void itemStateChanged( ItemEvent e )
            {
                if ( chkBoxLayerAlgorithm.isSelected() )
                {
                    textArea.setText( "Wybrano algorytm Warstwowy" );
                    CommonVariables.getInstance().GENERUJ_ALGORYTM_WARSTWOWY = true;
                }
                else
                {
                    textArea.setText( "Nie wybrano algorytm Warstwowy" );
                    CommonVariables.getInstance().GENERUJ_ALGORYTM_WARSTWOWY = false;
                }
            }
        } );
        /*        chkBoxLayerAlgorithm.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        if(chkBoxLayerAlgorithm.isSelected())
                        {
                            textArea.setText( "Wybrano algorytm Warstwowy" );
                        }else
                        {
                            textArea.setText( "Nie wybrano algorytm Warstwowy" );
                        }
                    }
                });*/

        chkBoxLayerAlgorithm.setSelected( true );
        panel_3a.add( chkBoxLayerAlgorithm );

        JPanel panel_3b = new JPanel();
        panel_3.add( panel_3b );
        panel_3b.setLayout( new GridLayout( 3, 1 ) );

        JLabel lblNewLabel_1 = new JLabel( "Typ pomiaru" );
        lblNewLabel_1.setFont( new Font( "Tahoma", Font.BOLD, 11 ) );
        lblNewLabel_1.setHorizontalAlignment( SwingConstants.CENTER );
        panel_3b.add( lblNewLabel_1 );

        chkBoxComputeComplexity = new JCheckBox( "Czas wykonania" );
        chkBoxComputeComplexity.addItemListener( new ItemListener()
        {
            public void itemStateChanged( ItemEvent e )
            {
                if ( chkBoxComputeComplexity.isSelected() )
                {
                    textArea.setText( "Wybrano pomiar z�o�ono�ci obliczeniowej" );
                    CommonVariables.getInstance().GENERUJ_POMIAR_ZLOZONOSCI_OBLICZENIOWEJ = true;
                }
                else
                {
                    textArea.setText( "Nie wybrano pomiar z�o�ono�ci obliczeniowej" );
                    CommonVariables.getInstance().GENERUJ_POMIAR_ZLOZONOSCI_OBLICZENIOWEJ = false;
                }
            }
        } );

        chkBoxComputeComplexity.setSelected( true );
        panel_3b.add( chkBoxComputeComplexity );

        chkBoxComputeMemory = new JCheckBox( "Z�o�ono�� pami�ciowa" );
        chkBoxComputeMemory.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(chkBoxComputeMemory.isSelected())
                {
                    CommonVariables.getInstance().GENERUJ_POMIAR_ZLOZONOSCI_PAMIECIOWEJ = true;
                }else
                {
                    CommonVariables.getInstance().GENERUJ_POMIAR_ZLOZONOSCI_PAMIECIOWEJ = false;
                }
            }
        });
        chkBoxComputeMemory.setEnabled( false );
        panel_3b.add( chkBoxComputeMemory );

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

        JLabel label_1 = new JLabel( "Liczba generowanych graf�w" );
        label_1.setBounds( 206, 8, 183, 14 );
        panel_7.add( label_1 );

        tfNumberOfGraphs = new JTextField();
        tfNumberOfGraphs.setColumns( 10 );
        tfNumberOfGraphs.setBounds( 10, 8, 48, 20 );
        panel_7.add( tfNumberOfGraphs );

        JPanel panel_8 = new JPanel();
        panel_10.add( panel_8 );
        panel_8.setLayout( new GridLayout( 1, 2 ) );

        rbRandomWeight = new JRadioButton( "wagi losowe" );
        rbRandomWeight.addItemListener( new ItemListener()
        {
            public void itemStateChanged( ItemEvent e )
            {
                if ( rbRandomWeight.isSelected() )
                {
                    textArea.setText( "Wybrano wag� losow�" );
                    CommonVariables.getInstance().CZY_WAGOWY = true;
                    CommonVariables.getInstance().WAGI_ROWNE_JEDEN = false;
                    
                }
                else
                {
                    textArea.setText( "Nie wybrano wag� losow�" );
                    CommonVariables.getInstance().CZY_WAGOWY = true;
                    CommonVariables.getInstance().WAGI_ROWNE_JEDEN = true;
                }
            }
        } );
        panel_8.add( rbRandomWeight );
        rbRandomWeight.setSelected( true );
        rbRandomWeight.setHorizontalAlignment( SwingConstants.LEFT );

        rbStateWeight = new JRadioButton( "wagi sta�e r�wne 1" );
        rbStateWeight.addItemListener( new ItemListener()
        {
            public void itemStateChanged( ItemEvent e )
            {
                if ( rbStateWeight.isSelected() )
                {
                    textArea.setText( "Wybrano sta�� wag�" );
                }
                else
                {
                    textArea.setText( "Nie wybrano sta�ej wagi" );
                }
            }
        } );
        panel_8.add( rbStateWeight );
        rbStateWeight.setHorizontalAlignment( SwingConstants.LEFT );

        ButtonGroup btButtonGroup = new ButtonGroup();
        btButtonGroup.add( rbRandomWeight );
        btButtonGroup.add( rbStateWeight );

        JPanel panel_9 = new JPanel();
        panel_10.add( panel_9 );
        panel_9.setLayout( new GridLayout( 2, 1 ) );

        JLabel lblNewLabel_4 = new JLabel( "G�sto�� grafu" );
        lblNewLabel_4.setHorizontalAlignment( SwingConstants.CENTER );
        panel_9.add( lblNewLabel_4 );

        sliderDensityGraph = new JSlider();
        sliderDensityGraph.addChangeListener( new ChangeListener()
        {
            public void stateChanged( ChangeEvent e )
            {
                int value = sliderDensityGraph.getValue();
                double setValue = value / 100.0;
                textArea.setText( "Slider value = " + setValue );
                CommonVariables.getInstance().GESTOSC_GRAFU = setValue;

            }
        } );
        sliderDensityGraph.setToolTipText( "Ustaw g�sto�� grafu z zakresu 0,01 - 1,00" );
        sliderDensityGraph.setSnapToTicks( true );
        sliderDensityGraph.setPaintTicks( true );
        sliderDensityGraph.setPaintLabels( true );
        sliderDensityGraph.setMinorTickSpacing( 1 );
        sliderDensityGraph.setValue( 0 );
        panel_9.add( sliderDensityGraph );

        JPanel panel_2 = new JPanel();
        panel_1.add( panel_2 );
        panel_2.setLayout( new GridLayout( 4, 1 ) );

        JPanel panel_4 = new JPanel();
        panel_2.add( panel_4 );
        panel_4.setBounds( 0, 92, 392, 45 );
        panel_4.setLayout( null );

        JLabel label = new JLabel( "Zakres wierzcho�k�w w serii" );
        label.setBounds( 202, 14, 187, 14 );
        panel_4.add( label );

        spinnerVertexFrom = new JSpinner();
        spinnerVertexFrom.setModel( new SpinnerNumberModel( 3, 3, 16, 1 ) );
        spinnerVertexFrom.setBounds( 36, 11, 39, 20 );
        panel_4.add( spinnerVertexFrom );

        spinnerVertextTo = new JSpinner();
        spinnerVertextTo.setModel( new SpinnerNumberModel( 16, 3, 16, 1 ) );
        spinnerVertextTo.setBounds( 119, 11, 39, 20 );
        panel_4.add( spinnerVertextTo );

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

        tfNumberOfMeasurements = new JTextField();
        tfNumberOfMeasurements.setBounds( 10, 11, 48, 20 );
        panel_6.add( tfNumberOfMeasurements );
        tfNumberOfMeasurements.setColumns( 10 );

        JLabel lblNewLabel_3 = new JLabel( "Ilo�� pomiar�w dla zadania" );
        lblNewLabel_3.setBounds( 204, 14, 185, 14 );
        panel_6.add( lblNewLabel_3 );

        JPanel panel_11 = new JPanel();
        panel_2.add( panel_11 );

        JLabel lblRozpocznijSymulacj = new JLabel( "Rozpocznij symulacj�" );
        lblRozpocznijSymulacj.setFont( new Font( "Tahoma", Font.BOLD, 11 ) );
        panel_11.add( lblRozpocznijSymulacj );

        JPanel panel_12 = new JPanel();
        panel_2.add( panel_12 );

        JButton btnStart = new JButton( "Start" );

        btnStart.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent arg0 )
            {
                textArea.setText( "Wci�ni�to Start i rozpocz�to symulacj�\n" );
                textArea.append( "Liczba generowanych graf�w = " + tfNumberOfGraphs.getText() + "\n" );
                textArea.append( "Ilo�� pomiar�w dla zadania = " + tfNumberOfMeasurements.getText() + "\n" );
                textArea.append( "Od = " + spinnerVertexFrom.getValue() + "\n" );
                textArea.append( "Do = " + spinnerVertextTo.getValue() + "\n" );
                
                CommonVariables.getInstance().LICZBA_GENEROWANYCH_GRAFOW_W_SERII = Integer.valueOf(tfNumberOfGraphs.getText());
                CommonVariables.getInstance().LICZBA_SERII_POMIAROW_DLA_JEDNEGO_ZADANIA = Integer.valueOf( tfNumberOfMeasurements.getText());
                CommonVariables.getInstance().SERIA_POMIAROW_OD = (int)spinnerVertexFrom.getValue();
                CommonVariables.getInstance().SERIA_POMIAROW_DO = (int)spinnerVertextTo.getValue();
                
                
                
            }
        } );
        btnStart.setForeground( new Color( 0, 0, 0 ) );
        panel_12.add( btnStart );
    }
    
    
    public static void runAlgorithms(){
        
    }
}
