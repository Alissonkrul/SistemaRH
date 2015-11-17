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
public class TablePermissoes extends AbstractTableModel {

    private List<Funcionario> funcionarios = new ArrayList();

    private String[] colunas = {"Nome", "Sobrenome", "RG", "CPF", "Telefone", "Departamento", "Cargo", "Nível"};

    public TablePermissoes() {
        funcionarios = new ArrayList<>();
    }

    public Funcionario edit(int linhas[]) throws Exception {
        for (int i = 0; i < linhas.length; i++) {
            Funcionario f = funcionarios.get(linhas[i]);
            return f;
        }
        return null;
    }

    public void refreshTable(Funcionario funcionario) {
        funcionarios = FuncionarioDAO.carregaPorCpf(funcionario);
        //this.sort();
        fireTableDataChanged();
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
        }
        return null;
    }

    public void sort() {
        Collections.sort(funcionarios, new Comparator<Funcionario>() {
            public int compare(Funcionario arg0, Funcionario arg1) {
                return arg0.getNome().compareToIgnoreCase(arg1.getNome());
            }
        });
    }

}
