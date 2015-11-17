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
public class TableFuncionarios extends AbstractTableModel {

    private List<Funcionario> funcionarios = new ArrayList();

    private String[] colunas = {"Nome", "Sobrenome", "RG", "CPF", "Telefone", "Departamento", "Cargo", "Nível", "Salário"};

    public TableFuncionarios() {
        funcionarios = new ArrayList<>();
        refreshTable();
    }
    
    public void refreshCpf(Funcionario f) {
        funcionarios = FuncionarioDAO.carregaPorCpf(f);
        fireTableDataChanged();
    }
    
    public void refreshRg(Funcionario f) {
        funcionarios = FuncionarioDAO.carregaPorRg(f);
        fireTableDataChanged();
    }
    
    public void refreshCargo(Funcionario f) {
        funcionarios = FuncionarioDAO.carregaPorCargo(f);
        fireTableDataChanged();
    }
    
    public void refreshNome(Funcionario f) {
        funcionarios = FuncionarioDAO.carregaPorNome(f);
        fireTableDataChanged();
    }
    
    public void refreshSobrenome(Funcionario f) {
        funcionarios = FuncionarioDAO.carregaPorSobrenome(f);
        fireTableDataChanged();
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
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 5:
                return Departamento.class;
            case 6:
                return Cargo.class;
            case 7:
                return String.class;
        }
        return super.getColumnClass(col);
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        return coluna!=8;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return funcionarios.get(linha).getNome();
            case 1:
                return funcionarios.get(linha).getSobrenome();
            case 2:
                return funcionarios.get(linha).getRg();
            case 3:
                return funcionarios.get(linha).getCpf();
            case 4:
                return funcionarios.get(linha).getTelefone();
            case 5:
                return funcionarios.get(linha).getDepartamento();
            case 6:
                return funcionarios.get(linha).getCargo();
            case 7:
                return funcionarios.get(linha).getCargo().getNivel();
            case 8:
                return funcionarios.get(linha).getCargo().getSalario();
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
