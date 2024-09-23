import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws BonusException {
        float maiorVenda = 0;
        int vencedor = 0;

        ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
        departamentos.add(new Departamento(1,0));
        departamentos.add(new Departamento(2,0));
        departamentos.add(new Departamento(3,0));

        Departamento telemarkting = departamentos.get(0);
        Departamento oficina = departamentos.get(1);
        Departamento externo = departamentos.get(2);

        ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
        vendedores.add(new Vendedor(1,100000.0f, 1,telemarkting.getId(),30000.0f));
        vendedores.add(new Vendedor(2,200000.0f, 1,telemarkting.getId(),20000.0f));
        vendedores.add(new Vendedor(3,300000.0f, 1, oficina.getId(), 20000.0f));
        vendedores.add(new Vendedor(3,300000.0f, 1, oficina.getId(), 20000.0f));
        vendedores.add(new Vendedor(3,100000.0f, 1, externo.getId(), 120000.0f));

        for(Vendedor vendedor:vendedores){
            vendedor.salario();

            if(vendedor.getDepartamento() == 1){
                telemarkting.totalVendas(vendedor.getVenda());
            }else if(vendedor.getDepartamento() == 2){
                oficina.totalVendas(vendedor.getVenda());
            }else if(vendedor.getDepartamento() == 3){
                externo.totalVendas(vendedor.getVenda());
            }
        }

        for(Departamento departamento:departamentos){
            if(departamento.getTotalVendas() > maiorVenda){
                maiorVenda = departamento.getTotalVendas();
                vencedor = departamento.getId();
            }
        }

        for(Vendedor vendedor:vendedores){
            if(vendedor.getDepartamento() == vencedor && vendedor.getSalario() < 150000){
                vendedor.setSalario(2000);
            }else{
                vendedor.setSalario(1000);
            }
            System.out.println(vendedor.getSalario());
        }

        Bonus bonus = new Bonus(vendedores);
    }
}
