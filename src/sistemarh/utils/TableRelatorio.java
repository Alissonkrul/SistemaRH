/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarh.utils;

import sistemarh.entidades.Funcionario;
import sistemarh.DAO.FuncionarioDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import sistemarh.entidades.Cargo;
import sistemarh.entidades.Departamento;

/**
 *
 * @author Alisson
 */
public class TableRelatorio extends AbstractTableModel {

    private List<Funcionario> funcionarios = new ArrayList();

    private String[] colunas = {"Nome", "Departamento", "Cargo", "Nível", "Salario", "Bonus Anual"};

    public TableRelatorio() {
        funcionarios = new ArrayList<>();
        refreshTable();
    }

    public void refreshTable() {
        funcionarios = FuncionarioDAO.carregarFuncionarios();
        //this.sort();
        fireTableDataChanged();
    }

    public void removeRow(int linha) {
        this.funcionarios.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
        refreshTable();
    }

    public void addRow(Funcionario p) {
        this.funcionarios.add(p);
        this.fireTableDataChanged();
        refreshTable();
    }

    public String getColumnName(int num) {
        return this.colunas[num];
    }

    @Override
    public int getRowCount() {
        return funcionarios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        return false;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return funcionarios.get(linha).getNome();
            case 1:
                return funcionarios.get(linha).getDepartamento();
            case 2:
                return funcionarios.get(linha).getCargo().getNome();
            case 3:
                return funcionarios.get(linha).getCargo().getNivel();
            case 4:
                return funcionarios.get(linha).getCargo().getSalario();
            case 5:
                return funcionarios.get(linha).calculaBonus();
        }
        return null;
    }

    @Override
    public void setValueAt(Object novoObj, int linhaIndex, int colunaIndex) {
        Funcionario fun = funcionarios.get(linhaIndex);

        switch (colunaIndex) {
            case 0:
                fun.setNome(novoObj.toString());
                break;
            case 1:
                fun.setSobrenome(novoObj.toString());
                break;
            case 2:
                fun.setRg(novoObj.toString());
                break;
            case 3:
                fun.setCpf(novoObj.toString());
                break;
            case 4:
                fun.setTelefone(novoObj.toString());
                break;
            case 5:
                fun.setDepartamento((Departamento) novoObj);
                break;
            case 6:
                fun.setCargo((Cargo) novoObj);
                break;
            case 7:
                fun.getCargo().setNivel((int) novoObj);
                break;
        }
        fun.update();
        this.refreshTable();
    }

    public void sort() {
        Collections.sort(funcionarios, new Comparator<Funcionario>() {
            public int compare(Funcionario arg0, Funcionario arg1) {
                return arg0.getNome().compareToIgnoreCase(arg1.getNome());
            }
        });
    }

    public void delete(int linhas[]) throws Exception {
        for (int i = 0; i < linhas.length; i++) {
            Funcionario funcionario = funcionarios.get(linhas[i]);
            funcionario.delete();
        }
        this.refreshTable();
    }

}
