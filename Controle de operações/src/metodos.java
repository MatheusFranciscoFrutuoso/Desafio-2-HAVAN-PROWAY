import java.sql.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class metodos {

	static Connection myConn = null;
	static Statement myStmt = null;
	static ResultSet myRs = null;
	public String moedaOrigem, moedaDestino, mostrarMoedas;
	public Double valor, valorDestino, conversao, convertido, valorOrigem, taxa;

	DecimalFormat formatarDecimal = new DecimalFormat("0.0000");
	Scanner sc = new Scanner(System.in);

	public static Statement conexao() throws SQLException {

		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gerenciaroperacoes", "proway", "proway");

		myStmt = myConn.createStatement();

		return myStmt;

	}

	public double calculadora(String moedaOrigem, String moedaDestino, Double valor) throws SQLException {

		myStmt = conexao();

		myRs = myStmt.executeQuery("SELECT valormoeda FROM cadastromoedas where nomemoeda = '" + moedaOrigem + "'");

		if (myRs != null && myRs.next()) {
			valorOrigem = myRs.getDouble("valormoeda");
		}

		myRs = myStmt.executeQuery("SELECT valormoeda FROM cadastromoedas where nomemoeda = '" + moedaDestino + "'");

		if (myRs != null && myRs.next()) {
			valorDestino = myRs.getDouble("valormoeda");
		}

		if (moedaDestino.equals("REAL")) {
			convertido = valor * valorOrigem;
		} else {
			if (moedaOrigem.equals("REAL")) {
				convertido = valor / valorDestino;
			} else {
				conversao = valor * valorOrigem;
				convertido = conversao / valorDestino;
			}
		}

		return convertido;

	}

	public double calculaTaxa(Double valor) {
		Double taxaCalculada;
		taxaCalculada = valor * 0.10;

		return taxaCalculada;
	}

	public void novaMoeda(String nomeMoeda, Double valorMoeda) throws SQLException {

		myStmt = conexao();

		myStmt.execute("INSERT INTO cadastromoedas " + "(nomemoeda, valormoeda) " + "VALUES " + "('" + nomeMoeda + "','"
				+ valorMoeda + "')");

		myRs = myStmt.executeQuery("select * from cadastromoedas");

	}

	public void registroOperacao(String nomeCliente, String moedaOrigem, String moedaDestino, Double valor,
			Double valorConvertido, Double taxaCobrada) throws SQLException {
		myStmt = conexao();

		myStmt.execute("INSERT INTO cadastroOperacoes "
				+ "(nomecliente, moedaorigem, moedadestino, valororiginal, valorconvertido, taxacobrada) " + "VALUES "
				+ "('" + nomeCliente + "','" + moedaOrigem + "','" + moedaDestino + "','" + valor + "','"
				+ valorConvertido + "','" + taxaCobrada + "')");

		myRs = myStmt.executeQuery("select * from cadastromoedas");

	}

	public void retornarOperacoes() throws SQLException {

		myStmt = conexao();

		myRs = myStmt.executeQuery("SELECT * FROM cadastrooperacoes");

		while (myRs.next()) {

			System.out.println("Codigo da operação: " + myRs.getInt("codigooperacao") + " Nome do cliente: "
					+ myRs.getString("nomecliente") + " Moeda de origem: " + myRs.getString("moedaorigem")
					+ " Moeda destino: " + myRs.getString("moedadestino") + " Data da operação: "
					+ myRs.getDate("dataoperacao") + " Valor original: " + myRs.getDouble("valororiginal")
					+ " Valor convertido: " + myRs.getDouble("valorconvertido") + " Taxa cobrada: "
					+ myRs.getDouble("taxacobrada"));

		}

	}

	public void retornarOperacoesCliente(String nome) throws SQLException {

		myStmt = conexao();

		myRs = myStmt.executeQuery("SELECT * FROM cadastrooperacoes where nomecliente = '" + nome + "' ");

		while (myRs.next()) {

			System.out.println("Codigo da operação: " + myRs.getInt("codigooperacao") + " Nome do cliente: "
					+ myRs.getString("nomecliente") + " Moeda de origem: " + myRs.getString("moedaorigem")
					+ " Moeda destino: " + myRs.getString("moedadestino") + " Data da operação: "
					+ myRs.getDate("dataoperacao") + " Valor original: " + myRs.getDouble("valororiginal")
					+ " Valor convertido: " + myRs.getDouble("valorconvertido") + " Taxa cobrada: "
					+ myRs.getDouble("taxacobrada"));

		}

	}

	public double valorTotalOperacoes() throws SQLException {

		Double valorTotal = 0.00;

		myStmt = conexao();

		myRs = myStmt.executeQuery("SELECT valorconvertido FROM cadastrooperacoes ");

		while (myRs.next()) {

			valorTotal += myRs.getDouble("valorconvertido");

		}

		return valorTotal;

	}

	public double valorTotalOperacoesCliente(String nome) throws SQLException {

		Double valorTotal = 0.00;

		myStmt = conexao();

		myRs = myStmt.executeQuery("SELECT valorconvertido FROM cadastrooperacoes where nomecliente = '" + nome + "' ");

		while (myRs.next()) {

			valorTotal += myRs.getDouble("valorconvertido");

		}

		return valorTotal;

	}

	public double valorTotalTaxa() throws SQLException {

		Double valorTotal = 0.00;

		myStmt = conexao();

		myRs = myStmt.executeQuery("SELECT taxacobrada FROM cadastrooperacoes");

		while (myRs.next()) {

			valorTotal += myRs.getDouble("taxacobrada");

		}

		return valorTotal;

	}

	public double valorTotalTaxaCliente(String nome) throws SQLException {

		Double valorTotal = 0.00;

		myStmt = conexao();

		myRs = myStmt.executeQuery("SELECT taxacobrada FROM cadastrooperacoes where nomecliente = '"+nome+"'");

		while (myRs.next()) {

			valorTotal += myRs.getDouble("taxacobrada");

		}

		return valorTotal;

	}

}
