package org.example.GUI;
import org.example.BusinessLogic.SimulationManager;
import org.example.model.Task;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Design extends JFrame {
    private JTextField nbClientsText;
    private JTextField nbQueuesText;
    private JTextField simuationIntervalText;
    private JTextField minArrTimeText;
    private JTextField maxArrTimeText;
    private JTextField minServiceTimeText;
    private JTextField maxServiceTimeText;
    private JButton startSimulationButton;
    private JButton closeButton;
    private JTextArea generatedTasksArea;
    private JTextArea queuesArea;
    private JComboBox strategy;
    private JLabel currentTimeLabel;
    private JLabel averageTimeLabel;
    private JLabel averageServiceTimeLabel;
    private JLabel peakHourLabel;
    private SimulationManager simulationManager;


    public Design() {
        this.setSize(900, 650); // dim fereastra
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);  // pt pozitionare manuala

        // TITLU
        JLabel titleLabel = new JLabel("QUEUES MANAGEMENT APPLICATION");
        titleLabel.setBounds(100, 10, 300, 30);
        this.add(titleLabel);

        // Număr de clienți
        JLabel nbClientsLabel = new JLabel("Number of Clients:");
        nbClientsLabel.setBounds(50, 50, 150, 30);
        this.add(nbClientsLabel);
        nbClientsText = new JTextField();
        nbClientsText.setBounds(200, 50, 100, 30);
        this.add(nbClientsText);

        // Număr de cozi
        JLabel nbQueuesLabel = new JLabel("Number of Queues:");
        nbQueuesLabel.setBounds(50, 90, 150, 30);
        this.add(nbQueuesLabel);
        nbQueuesText = new JTextField();
        nbQueuesText.setBounds(200, 90, 100, 30);
        this.add(nbQueuesText);

        // Intervalul de simulare
        JLabel simIntervalLabel = new JLabel("Simulation Interval (max time):");
        simIntervalLabel.setBounds(50, 130, 200, 30);
        this.add(simIntervalLabel);
        simuationIntervalText = new JTextField();
        simuationIntervalText.setBounds(250, 130, 50, 30);
        this.add(simuationIntervalText);

        // Timpul minim și maxim de sosire
        JLabel minArrTimeLabel = new JLabel("Min Arrival Time:");
        minArrTimeLabel.setBounds(50, 170, 150, 30);
        this.add(minArrTimeLabel);
        minArrTimeText = new JTextField();
        minArrTimeText.setBounds(200, 170, 50, 30);
        this.add(minArrTimeText);

        JLabel maxArrTimeLabel = new JLabel("Max Arrival Time:");
        maxArrTimeLabel.setBounds(300, 170, 150, 30);
        this.add(maxArrTimeLabel);
        maxArrTimeText = new JTextField();
        maxArrTimeText.setBounds(450, 170, 50, 30);
        this.add(maxArrTimeText);

        // Timpul minim și maxim de servire
        JLabel minServiceTimeLabel = new JLabel("Min Service Time:");
        minServiceTimeLabel.setBounds(50, 210, 150, 30);
        this.add(minServiceTimeLabel);
        minServiceTimeText = new JTextField();
        minServiceTimeText.setBounds(200, 210, 50, 30);
        this.add(minServiceTimeText);

        JLabel maxServiceTimeLabel = new JLabel("Max Service Time:");
        maxServiceTimeLabel.setBounds(300, 210, 150, 30);
        this.add(maxServiceTimeLabel);
        maxServiceTimeText = new JTextField();
        maxServiceTimeText.setBounds(450, 210, 50, 30);
        this.add(maxServiceTimeText);

        //strategy
        JLabel strategyLabel = new JLabel("Select Strategy:");
        strategyLabel.setBounds(500, 210, 150, 30);
        this.add(strategyLabel);

        strategy = new JComboBox<>(new String[]{"Shortest Queue", "Shortest Time"});
        strategy.setBounds(600, 210, 150, 30);
        this.add(strategy);

        //zona in care vor fi vizibile Task urile
        generatedTasksArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(generatedTasksArea);
        scrollPane.setBounds(50, 250, 600, 100);
        // scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane);

        ////////
        currentTimeLabel = new JLabel("Current Time: 0");
        currentTimeLabel.setBounds(700, 50, 150, 30);
        this.add(currentTimeLabel);

        averageTimeLabel = new JLabel("Average Waiting Time: ");
        averageTimeLabel.setBounds(700, 90, 200, 30);
        this.add(averageTimeLabel);

        averageServiceTimeLabel = new JLabel(("Average Service Time: "));
        averageServiceTimeLabel.setBounds(700, 130, 200, 30);
        this.add(averageServiceTimeLabel);

        peakHourLabel = new JLabel("Peak Hour: ");
        peakHourLabel.setBounds(700, 170, 200, 30);
        this.add(peakHourLabel);


        //zona in care vor fi vizibile cozile
        queuesArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(queuesArea);
        scroll.setBounds(50, 400, 600, 100);
        this.add(scroll);

        // Buton pentru a iniția simularea
        startSimulationButton = new JButton("Start Simulation");
        startSimulationButton.setBounds(150, 570, 150, 30);
        startSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validarea valorilor introduse de utilizator
                try {
                    int nbClients = Integer.parseInt(nbClientsText.getText());
                    int nbQueues = Integer.parseInt(nbQueuesText.getText());
                    int simInterval = Integer.parseInt(simuationIntervalText.getText());
                    int minArrTime = Integer.parseInt(minArrTimeText.getText());
                    int maxArrTime = Integer.parseInt(maxArrTimeText.getText());
                    int minServiceTime = Integer.parseInt(minServiceTimeText.getText());
                    int maxServiceTime = Integer.parseInt(maxServiceTimeText.getText());

                    // Verificarea dacă valorile sunt valide (numere întregi)
                    if (nbClients <= 0 || nbQueues <= 0 || simInterval <= 0 ||
                            minArrTime < 0 || maxArrTime < 0 || minServiceTime < 0 || maxServiceTime < 0 ||
                            minArrTime > maxArrTime || minServiceTime > maxServiceTime) {

                        JOptionPane.showMessageDialog(null, "Introduceți valori valide pentru parametrii de configurare!", "Eroare", JOptionPane.ERROR_MESSAGE);
                        return;  // Ieșim din metoda curentă dacă valorile nu sunt valide
                    }

                    // Aici va trebui să inițiem simularea cu parametrii introdusi de utilizator
                    // Implementați logica pentru a procesa input-ul și a începe simularea
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Introduceți valori întregi pentru parametrii de configurare!", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;  // Ieșim din metoda curentă dacă sunt introduse caractere care nu sunt numere
                }
                //conectam si initializam SimulationManager
                simulationManager = new SimulationManager(Design.this);
                simulationManager.startButton();
            }
        });
        this.add(startSimulationButton);


        // Buton pentru a închide aplicația
        closeButton = new JButton("Close");
        closeButton.setBounds(320, 570, 100, 30);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(closeButton);
    }
    public SimulationManager getSimulationManager() {
        return simulationManager;
    }
    public void addGeneretedTasks(List<Task> generatedTasks) {
        int i = 0;
        StringBuilder taskInfo = new StringBuilder();
        for (Task task : generatedTasks) {
            i++;
            if (i % 7 == 0) {
                taskInfo.append(task.toString()).append("\n");
            }
            taskInfo.append(task.toString()).append("; ");
        }
        generatedTasksArea.setText(taskInfo.toString());
    }



    public int getNbClientsText() {
        String text = nbClientsText.getText();
        if (text.isEmpty()) {
            return 0;  // sau o altă valoare implicită
        }
        return Integer.parseInt(text);
    }

    public void setCurrentTimeLabel(String currentTimeLabel) {
        this.currentTimeLabel.setText(currentTimeLabel);
    }

    public void setAverageTimeLabel(String averageTimeLabel) {
        this.averageTimeLabel.setText(averageTimeLabel);
    }

    public void setSimulationManager(SimulationManager simulationManager) {
        this.simulationManager = simulationManager;
    }

    public int getNbQueuesText() {
        String text = nbQueuesText.getText();
        if (text.isEmpty()) {
            return 0;  // sau o altă valoare implicită
        }
        return Integer.parseInt(text);
    }

    public int getSimuationIntervalText() {
        String text = simuationIntervalText.getText();
        if (text.isEmpty()) {
            return 0;
        } else
            return Integer.parseInt(text);
    }

    public int getMinArrTimeText() {
        String text = minArrTimeText.getText();
        if (text.isEmpty()) {
            return 0;
        } else
            return Integer.parseInt(text);
    }

    public int getMaxArrTimeText() {
        String text = maxArrTimeText.getText();
        if (text.isEmpty()) {
            return 0;
        } else
            return Integer.parseInt(text);
    }

    public int getMinServiceTimeText() {
        String text = minServiceTimeText.getText();
        if (text.isEmpty()) {
            return 0;
        } else
            return Integer.parseInt(text);
    }

    public int getMaxServiceTimeText() {
        String text = maxServiceTimeText.getText();
        if (text.isEmpty()) {
            return 0;
        } else
            return Integer.parseInt(text);
    }

    public void setNbClientsText(JTextField nbClientsText) {
        this.nbClientsText = nbClientsText;
    }

    public void setNbQueuesText(JTextField nbQueuesText) {
        this.nbQueuesText = nbQueuesText;
    }

    public void setSimuationIntervalText(JTextField simuationIntervalText) {
        this.simuationIntervalText = simuationIntervalText;
    }

    public void setMinArrTimeText(JTextField minArrTimeText) {
        this.minArrTimeText = minArrTimeText;
    }

    public void setMaxArrTimeText(JTextField maxArrTimeText) {
        this.maxArrTimeText = maxArrTimeText;
    }

    public void setMinServiceTimeText(JTextField minServiceTimeText) {
        this.minServiceTimeText = minServiceTimeText;
    }

    public JTextArea getGeneratedTasksArea() {
        return generatedTasksArea;
    }

    public void setStartSimulationButton(JButton startSimulationButton) {
        this.startSimulationButton = startSimulationButton;
    }

    public void setAverageServiceTimeLabel(String averageServiceTimeLabel) {
        this.averageServiceTimeLabel.setText(averageServiceTimeLabel);
    }

    public void setPeakHourLabel(JLabel peakHourLabel) {
        this.peakHourLabel = peakHourLabel;
    }

    public void setCloseButton(JButton closeButton) {
        this.closeButton = closeButton;
    }

    public void setGeneratedTasksArea(JTextArea generatedTasksArea) {
        this.generatedTasksArea = generatedTasksArea;
    }

    public void setMaxServiceTimeText(JTextField maxServiceTimeText) {
        this.maxServiceTimeText = maxServiceTimeText;
    }

    public JButton getStartSimulationButton() {
        return startSimulationButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public JTextArea getQueuesArea() {
        return queuesArea;
    }

    public String getStrategy() {
        String selectedStrategy = (String) strategy.getSelectedItem();
        return selectedStrategy;
    }

    public void setQueuesArea(JTextArea queuesArea) {
        this.queuesArea = queuesArea;
    }

    public void setStrategy(JComboBox strategy) {
        this.strategy = strategy;
    }

    public JLabel getCurrentTimeLabel() {
        return currentTimeLabel;
    }

    public JLabel getAverageTimeLabel() {
        return averageTimeLabel;
    }

    public JLabel getPeakHourLabel() {
        return peakHourLabel;
    }

    public void setCurrentTimeLabel(JLabel currentTimeLabel) {
        this.currentTimeLabel = currentTimeLabel;
    }

    public void setAverageTimeLabel(JLabel averageTimeLabel) {
        this.averageTimeLabel = averageTimeLabel;
    }

    public void setPeakHourLabel(String peakHourLabel) {
        this.peakHourLabel.setText(peakHourLabel);
    }
}