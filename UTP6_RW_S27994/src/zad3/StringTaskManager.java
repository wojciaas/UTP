package zad3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.*;

public class StringTaskManager extends JFrame {

    private final ExecutorService executorService;
    private final ArrayList<Future<StringTask>> futureStringTasks;
    private final DefaultListModel<String> listModel;
    private final JList<String> taskJList;

    public StringTaskManager() {
        super("String Task Manager");
        executorService = Executors.newCachedThreadPool();
        futureStringTasks = new ArrayList<>();
        listModel = new DefaultListModel<>();
        taskJList = new JList<>(listModel);
        JButton startButton = new JButton("Start Task");
        JButton cancelButton = new JButton("Cancel Task");
        JButton resultButton = new JButton("Show Result");
        JButton statusButton = new JButton("Check Status");

        startButton.addActionListener(new StartAction());
        cancelButton.addActionListener(new CancelAction());
        resultButton.addActionListener(new ResultAction());
        statusButton.addActionListener(new StatusAction());

        setLayout(new BorderLayout());
        add(new JScrollPane(taskJList), BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4, 1));
        buttonsPanel.add(startButton);
        buttonsPanel.add(cancelButton);
        buttonsPanel.add(resultButton);
        buttonsPanel.add(statusButton);

        add(buttonsPanel, BorderLayout.EAST);
        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class StartAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            StringTask task = new StringTask("zad3", 70000);
            futureStringTasks.add(executorService.submit(task, task));
            listModel.addElement("Task " + futureStringTasks.size() + ": " + task.getState());
        }
    }

    private class CancelAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = taskJList.getSelectedIndex();
            if (index != -1) {
                futureStringTasks.get(index).cancel(true);
                listModel.setElementAt("Task " + (index + 1) + ": ABORTED", index);
            }
        }
    }

    private class ResultAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = taskJList.getSelectedIndex();
            if (index != -1) {
                try {
                    JOptionPane.showMessageDialog(StringTaskManager.this, "Result length: " + futureStringTasks.get(index).get().getCounter(), "Task " + (index + 1), JOptionPane.INFORMATION_MESSAGE);
                } catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private class StatusAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = taskJList.getSelectedIndex();
            if (index != -1) {
                if (futureStringTasks.get(index).isDone()) {
                    listModel.setElementAt("Task " + (index + 1) + ": READY", index);
                } else if (futureStringTasks.get(index).isCancelled()) {
                    listModel.setElementAt("Task " + (index + 1) + ": ABORTED", index);
                } else {
                    listModel.setElementAt("Task " + (index + 1) + ": RUNNING", index);
                }
            }
        }
    }
}