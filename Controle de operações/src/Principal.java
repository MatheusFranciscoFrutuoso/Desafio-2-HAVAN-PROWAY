import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class Principal {

	public static void main(String[] args) throws SQLException {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		String moedaOrigem, moedaDestino, mostrarMoedas, nomeCliente, entrada;
		Double valor, valorDestino, conversao, convertido, valorOrigem, taxa;
		int opcao;

		do {

			DecimalFormat formatarDecimal = new DecimalFormat("0.0000");

			metodos metodos = new metodos();

			entrada = JOptionPane
					.showInputDialog("\n\tMenu\n\t\n1.Registrar uma operação\n" + "\t2.Registrar uma nova moeda\n"
							+ "\t3.Retornar as operações feitas\n" + "\t4.Retornar as operações feitas por um cliente\n" + "\t5.Valor total das operações\n"
							+ "\t6.Valor total das operações de um cliente\n" + "\t7.Valor total das taxas cobradas\n" + "\t8.Valor total das taxas cobradas de um cliente\n" + "\t\n9.Sair");
			opcao = Integer.parseInt(entrada);

			switch (opcao) {
			case 1:

				nomeCliente = JOptionPane.showInputDialog("Insira o nome do cliente").toUpperCase();
				moedaOrigem = JOptionPane.showInputDialog("Insira a moeda de origem").toUpperCase();
				moedaDestino = JOptionPane.showInputDialog("Insira a moeda de destino").toUpperCase();
				valor = Double.parseDouble(JOptionPane.showInputDialog("Insira o valor a ser convertido"));

				convertido = metodos.calculadora(moedaOrigem, moedaDestino, valor);
				taxa = metodos.calculaTaxa(convertido);
				metodos.registroOperacao(nomeCliente, moedaOrigem, moedaDestino, valor, convertido, taxa);

				break;

			case 2:

				String nomeMoeda;
				Double valorMoeda;

				nomeMoeda = JOptionPane.showInputDialog("Insira o nome da moeda");
				valorMoeda = Double.parseDouble(JOptionPane.showInputDialog("Insira o valor da moeda em reais"));
				metodos.novaMoeda(nomeMoeda, valorMoeda);
				break;

			case 3:

				metodos.retornarOperacoes();
				break;

			case 4:

				nomeCliente = JOptionPane.showInputDialog("Insira o nome do cliente");
				metodos.retornarOperacoesCliente(nomeCliente);
				break;

			case 5:

				System.out.println("Valor total das operações: " + formatarDecimal.format(metodos.valorTotalOperacoes()));
				break;
			case 6:

				nomeCliente = JOptionPane.showInputDialog("Insira o nome do cliente");
				System.out.println(
						"Valor total das operações do cliente " + nomeCliente + ": " + formatarDecimal.format(metodos.valorTotalOperacoes()));
				break;
			case 7:

				System.out.println(
						"Valor total das taxa cobradas: " + formatarDecimal.format(metodos.valorTotalTaxa()));
				break;
			case 8:

				nomeCliente = JOptionPane.showInputDialog("Insira o nome do cliente");
				System.out.println(
						"Valor total das taxas cobradas do cliente " + nomeCliente + ": " + formatarDecimal.format(metodos.valorTotalTaxaCliente(nomeCliente)));
				break;
			}

		} while (opcao != 9);

	}

}
