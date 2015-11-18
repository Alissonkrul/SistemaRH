/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarh.utils;

import sistemarh.entidades.Departamento;
import sistemarh.DAO.DepartamentoDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alisson
 */
public class TableDepartamentosDirige extends AbstractTableModel {

    private List<Departamento> departamentos = new ArrayList();

    public void add(String name) {
        Departamento noveDepartamento = new Departamento(name);
        noveDepartamento.add();
        this.refreshTable();
    }

    public void addDep(List<Departamento> list) {
        for (Departamento departamento : list) {
            departamentos.add(departamento);
        }
        this.fireTableDataChanged();
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }
    
    public void setDepartamentos(List<Departamento> listDep) {
         departamentos = listDep;
    }

    public List<Departamento> remove(int rows[]) {
        List<Departamento> deps = new ArrayList();
        for (int i = 0; i < rows.length; i++) {  
            deps.add(departamentos.remove(rows[i]-i));
        }
        fireTableDataChanged();
        return deps;
    }

    private String[] colunas = {"Nome"};

    public TableDepartamentosDirige() {
        departamentos = new ArrayList<>();
        refreshTable();
    }

    public void refreshTable() {
        //departamentos = DepartamentoDAO.carregarDepartamentos();
        this.sort();
        fireTableDataChanged();
    }

    public void removeRow(int linha) {
        this.departamentos.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
        refreshTable();
    }

    public void addRow(Departamento p) {
        this.departamentos.add(p);
        this.fireTableDataChanged();
        refreshTable();
    }

    public String getColumnName(int num) {
        return this.colunas[num];
    }

    @Override
    public int getRowCount() {
        return departamentos.size();
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
                return departamentos.get(linha).getNome();
        }
        return null;
    }

    @Override
    public void setValueAt(Object novoNome, int linhaIndex, int colunaIndex) {
        Departamento dep = departamentos.get(linhaIndex);
        dep.setNome(novoNome.toString());
        dep.update();
        this.refreshTable();
    }

    public void sort() {
        Collections.sort(departamentos, new Comparator<Departamento>() {
            public int compare(Departamento arg0, Departamento arg1) {
                return arg0.getNome().compareToIgnoreCase(arg1.getNome());
            }
        });
    }

    public void delete(int linhas[]) throws Exception {
        for (int i = 0; i < linhas.length; i++) {
            Departamento departamento = departamentos.get(linhas[i]);
            departamento.delete();
        }
        this.refreshTable();
    }

}
