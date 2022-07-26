
package DTO;


public class DevolverLivroDTO {
    private int idDevolucao;
    private int idEmprestimo;
    private String dataEntregue;

    public int getIdDevolucao() {
        return idDevolucao;
    }

    public void setIdDevolucao(int idDevolucao) {
        this.idDevolucao = idDevolucao;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public String getDataEntregue() {
        return dataEntregue;
    }

    public void setDataEntregue(String dataEntregue) {
        this.dataEntregue = dataEntregue;
    }
    
    
}
