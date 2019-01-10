package mdsd.server.view;

import mdsd.StartObserver;
import mdsd.server.controller.MissionController;
import mdsd.server.model.ServerModel;
import simbad.gui.Simbad;
import simbad.sim.Agent;
import simbad.sim.CameraSensor;
import simbad.sim.RobotFactory;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class UI {

    private JLabel pointsLabel = new JLabel();
    private File file;
    private MissionController missionController;
    private StartObserver startObserver;
    private  ServerModel model;

    public UI(MissionController mc, StartObserver startObserver, ServerModel model){
        this.missionController = mc;
        this.startObserver = startObserver;
        this.model = model;
    }

    public void createFrame() {
        MyInternalFrame frame = new MyInternalFrame();
        frame.setVisible(true);

        JPanel containerPanel = new JPanel( new GridBagLayout() );

        JPanel jPanel1 = new JPanel();
        JPanel mainJpanel = new JPanel();
        JPanel jPanel2 = new JPanel();

        mainJpanel.setLayout(new BoxLayout(mainJpanel, 1));
        jPanel1.setLayout(new BoxLayout(jPanel1, 0));
        jPanel2.setLayout(new BoxLayout(jPanel2, 0));

        JLabel jLabel = new JLabel("Current reward points: ");
        jPanel1.add(jLabel);

        jPanel1.add(pointsLabel);

        JButton stop = new JButton("Stop Rovers");
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("All rovers are stopped");
                missionController.stopAllRovers();
            }
        });
        jPanel2.add(stop);

        JButton select = new JButton(" Select XML");
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
    //            FileChooser fileChooser = new FileChooser();
    //            File file = fileChooser.showOpenDialog();


                File xmlPath = new File("../MDSD_group18/src/mdsd/server/controller");
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getParentDirectory(xmlPath));
                jfc.setDialogTitle("Select an XML file");
                jfc.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("XML file", "xml");
                jfc.addChoosableFileFilter(filter);

                int returnValue = jfc.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // System.out.println("Selected new XML file: " + jfc.getSelectedFile().getPath());
                    // file = jfc.getSelectedFile();

                    missionController.readMissionsXML();
                    startObserver.start();
                }

            }
        });
        jPanel2.add(select);

        mainJpanel.add(jPanel1);
        mainJpanel.add(jPanel2);
        containerPanel.add(mainJpanel, new GridBagConstraints());
        frame.setContentPane(containerPanel);

        // Every JInternalFrame must be added to content pane using JDesktopPane
        Simbad.getSimbadInstance().getDesktopPane().add(frame);

        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }

    }

    class MyInternalFrame extends JInternalFrame {

        public MyInternalFrame() {
            super("User Interface", false,
                    false,
                    false,
                    false);
            setSize(260, 100);
            // Set the window's location.
            setLocation(20, 500);

        }
    }

    public void setPoints() {
        pointsLabel.setText(String.valueOf(model.getRewardPoints()));
    }

    public File getXmlFile(){
        return file;
    }
}

