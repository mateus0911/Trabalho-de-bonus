import java.util.ArrayList;
import java.util.List;


class Funcionario {
    private int id;
    private double salario;
    private String cargo;
    private int departamentoId;

    public Funcionario(int id, double salario, String cargo, int departamentoId) {
        this.id = id;
        this.salario = salario;
        this.cargo = cargo;
        this.departamentoId = departamentoId;
    }

    public int getId() {
        return id;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public int getDepartamentoId() {
        return departamentoId;
    }
}


class Departamento {
    private int id;
    private double vendas;

    public Departamento(int id, double vendas) {
        this.id = id;
        this.vendas = vendas;
    }

    public int getId() {
        return id;
    }

    public double getVendas() {
        return vendas;
    }
}


class TabelaVaziaException extends Exception {
    public TabelaVaziaException(String message) {
        super(message);
    }
}


class NenhumFuncionarioElegivelException extends Exception {
    public NenhumFuncionarioElegivelException(String message) {
        super(message);
    }
}


public class BonusModule {

    public static int calcularBonus(List<Funcionario> funcionarios, List<Departamento> departamentos) throws TabelaVaziaException, NenhumFuncionarioElegivelException {
        if (funcionarios.isEmpty() || departamentos.isEmpty()) {
            throw new TabelaVaziaException("Tabelas de funcionários ou departamentos estão vazias");
        }

        
        double maiorVenda = 0;
        List<Integer> departamentosComMaiorVenda = new ArrayList<>();
        for (Departamento dep : departamentos) {
            if (dep.getVendas() > maiorVenda) {
                maiorVenda = dep.getVendas();
                departamentosComMaiorVenda.clear();
                departamentosComMaiorVenda.add(dep.getId());
            } else if (dep.getVendas() == maiorVenda) {
                departamentosComMaiorVenda.add(dep.getId());
            }
        }

        
        boolean funcionarioElegivel = false;
        for (Funcionario func : funcionarios) {
            if (departamentosComMaiorVenda.contains(func.getDepartamentoId())) {
                funcionarioElegivel = true;
                if (func.getSalario() >= 150000 || func.getCargo().equalsIgnoreCase("gerente")) {
                    func.setSalario(func.getSalario() + 1000);
                } else {
                    func.setSalario(func.getSalario() + 2000);
                }
            }
        }

        if (!funcionarioElegivel) {
            throw new NenhumFuncionarioElegivelException("Nenhum funcionário elegível para bônus nos departamentos com maior venda");
        }

        return 0; 
    }

    public static void main(String[] args) {
        try {
            
            List<Funcionario> funcionarios = new ArrayList<>();
            funcionarios.add(new Funcionario(1, 140000, "desenvolvedor", 1));
            funcionarios.add(new Funcionario(2, 160000, "gerente", 2));
            funcionarios.add(new Funcionario(3, 120000, "analista", 1));

            List<Departamento> departamentos = new ArrayList<>();
            departamentos.add(new Departamento(1, 50000));
            departamentos.add(new Departamento(2, 50000));

            
            int resultado = calcularBonus(funcionarios, departamentos);
            System.out.println("Resultado do cálculo de bônus: " + resultado);

            
            for (Funcionario func : funcionarios) {
                System.out.println("Funcionário ID: " + func.getId() + " | Novo Salário: " + func.getSalario());
            }
        } catch (TabelaVaziaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (NenhumFuncionarioElegivelException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
