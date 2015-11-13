/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarh.utils;

import sistemarh.entidades.Cargo;
import sistemarh.DAO.CargoDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import sistemarh.entidades.Cargo;

/**
 *
 * @author Alisson
 */
public class TableSalarios extends AbstractTableModel {

    private List<Cargo> cargosSalarios = new ArrayList();

    private String[] colunas = {"Cargo", "Nivel", "Valor"};

    public TableSalarios() {
        cargosSalarios = new ArrayList<>();
        refreshTable();
    }

    public void refreshTable() {
        cargosSalarios = CargoDAO.carregarCargos();
        this.sort();
        fireTableDataChanged();
    }

    public void removeRow(int linha) {
        this.cargosSalarios.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
        refreshTable();
    }

    public void addRow(Cargo p) {
        this.cargosSalarios.add(p);
        this.fireTableDataChanged();
        refreshTable();
    }

    public String getColumnName(int num) {
        return this.colunas[num];
    }

    @Override
    public int getRowCount() {
        return cargosSalarios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        return coluna == 2;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return cargosSalarios.get(linha).getNome();

            case 1:
                return cargosSalarios.get(linha).getNivel();
                
            case 2:
                return cargosSalarios.get(linha).getSalario();
        }
        return null;
    }

    @Override
    public void setValueAt(Object novoNome, int linhaIndex, int colunaIndex) {
        Cargo cargo = cargosSalarios.get(linhaIndex);
        cargo.updateSalario(novoNome.toString());
        //cargo.update();
        this.refreshTable();
    }

    public void sort() {
        Collections.sort(cargosSalarios, new Comparator<Cargo>() {
            public int compare(Cargo arg0, Cargo arg1) {
                return arg0.getNome().compareToIgnoreCase(arg1.getNome());
            }
        });
    }

}
