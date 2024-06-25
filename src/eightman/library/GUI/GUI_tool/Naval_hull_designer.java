package eightman.library.GUI.GUI_tool;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static eightman.library.GUI.Main_GUI.modPathMap;

public class Naval_hull_designer extends JFrame {
    public Naval_hull_designer() {
        setTitle("Naval Hull Designer");
        setSize(650, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        // mod選択プルダウン
        JComboBox<String> modList = new JComboBox<>(modPathMap.keySet().toArray(new String[0]));
        add(modList);

        // 艦種スロットが必要かどうかのラジオボタン
        JRadioButton needSlotButton = new JRadioButton("Need Slot");
        JRadioButton noNeedSlotButton = new JRadioButton("No Need Slot");
        ButtonGroup group = new ButtonGroup();
        group.add(needSlotButton);
        group.add(noNeedSlotButton);

        // 艦種追加のための入力エリアと追加ボタン
        JTextField shipTypeField = new JTextField(10);
        JButton addButton = new JButton("Add");

        // 削除可能な艦種選択プルダウン
        ArrayList<String> shipTypes = new ArrayList<>();
        shipTypes.add("Type1");
        shipTypes.add("Type2");
        shipTypes.add("Type3");
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(shipTypes.toArray(new String[0]));
        JComboBox<String> shipTypeList = new JComboBox<>(model);
        JButton deleteButton = new JButton("Delete");

        addButton.addActionListener(e -> {
            String newType = shipTypeField.getText();
            if (!newType.isEmpty()) {
                shipTypes.add(newType);
                model.addElement(newType);
            }
        });

        deleteButton.addActionListener(e -> {
            String selectedType = (String) shipTypeList.getSelectedItem();
            shipTypes.remove(selectedType);
            model.removeElement(selectedType);
        });

        // 上段と下段の要素数を設定するプルダウンメニュー
        Integer[] elementNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        JComboBox<Integer> upperElementNumber = new JComboBox<>(elementNumbers);
        JComboBox<Integer> lowerElementNumber = new JComboBox<>(elementNumbers);

        // コンポーネントをフレームに追加
        add(needSlotButton);
        add(noNeedSlotButton);
        add(shipTypeField);
        add(addButton);
        add(shipTypeList);
        add(deleteButton);
        add(new JLabel("Upper Element Number:"));
        add(upperElementNumber);
        add(new JLabel("Lower Element Number:"));
        add(lowerElementNumber);
    }
}