package com.tia.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

import com.framework.Dia;
import com.framework.Diretorios;
import com.tia.controller.constantes.Persistencia;
import com.tia.controller.constantes.Turno;
import com.tia.model.Aula;

public class AulaDataAccess implements DataAccessObject<Aula> {

	DisciplinaDataAccess discDAO = new DisciplinaDataAccess();
	ProfessorDataAccess profDAO = new ProfessorDataAccess();
	SalaDataAccess salaDAO = new SalaDataAccess();
	SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
	Date data;

	@Override
	public Persistencia gravar(Aula e) {
		BufferedWriter writer;
		String dir = Diretorios.AULA + "\\" + e.getDia();
		File arquivo = new File(dir);
		if (!arquivo.exists())
			arquivo.mkdirs();

		if (validaNovoRegistro(e)) {
			try {
				writer = new BufferedWriter(new FileWriter(arquivo + "\\"
						+ e.getIdAula() + ".txt"));
				writer.write(String.valueOf(e.getIdAula()));
				writer.newLine();
				writer.write(String.valueOf(e.getDisc().getIdDisciplina()));
				writer.newLine();
				writer.write(String.valueOf(e.getProf().getId()));
				writer.newLine();
				writer.write(String.valueOf(e.getSala().getId_sala()));
				writer.newLine();
				writer.write(String.valueOf(e.getTurno().name()));
				writer.newLine();
				writer.write(e.getDia().name());
				writer.newLine();
				writer.write(e.getIni().toString());
				writer.newLine();
				writer.write(e.getFim().toString());
				writer.close();
				return Persistencia.GRAVADO;
			} catch (IOException e1) {
				System.err.println(e1.getMessage());
				e1.printStackTrace();
				return Persistencia.ERRO;
			}
		}
		return Persistencia.DUPLICADO;
	}

	@Override
	public ListaEncadeada<Aula> lerTodos() {
		File[] diretorios = new File(Diretorios.AULA.toString()).listFiles();
		ListaEncadeada<Aula> lista = new ListaEncadeada<Aula>();
		BufferedReader reader;
		Aula aula;
		for (File diretorio : diretorios) {
			if (diretorio.isDirectory()) {
				File[] arquivos = (diretorio).listFiles();
				for (File arquivo : arquivos) {
					try {
						reader = new BufferedReader(new FileReader(arquivo));
						aula = new Aula();
						aula.setIdAula(Integer.parseInt(reader.readLine()));
						aula.setDisc(discDAO.buscar((Integer.parseInt(reader.readLine()))));
						aula.setProf(profDAO.buscar(Integer.parseInt(reader.readLine())));
						aula.setSala(salaDAO.buscar(Integer.parseInt(reader.readLine())));
						aula.setTurno(Turno.valueOf(reader.readLine()));
						aula.setDia(Dia.valueOf(reader.readLine()));
						try {
							data = formatador.parse(reader.readLine());
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						aula.setIni(new Time(data.getTime()));
						try {
							data = formatador.parse(reader.readLine());
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						aula.setFim(new Time(data.getTime()));
						lista.addFim(aula);
						reader.close();
					} catch (IOException e) {
						System.err.println(e.getMessage());
						e.printStackTrace();
					}

				}
			}
		}
		return lista;
	}

	@Override
	public Persistencia deletar(Aula e) {
		String path = Diretorios.AULA + "\\" + e.getDia() + "\\"
				+ e.getIdAula() + ".txt";
		File arquivo = new File(path);
		if (arquivo.delete())
			return Persistencia.REMOVIDO;
		return Persistencia.ERRO;
	}

	@Override
	public Aula buscar(int id) {
		Aula aula = new Aula();
		BufferedReader reader;
		String path = null;
		File[] diretorios = new File(Diretorios.AULA.toString()).listFiles();
		for (File diretorio : diretorios) {
			File[] arquivos = diretorio.listFiles();
			for (File arquivo : arquivos) {
				if (arquivo.getName().equalsIgnoreCase(id + ".txt"))
					path = arquivo.getPath();
			}
		}

		try {
			reader = new BufferedReader(new FileReader(path));
			aula = new Aula();
			aula.setIdAula(Integer.parseInt(reader.readLine()));
			aula.setDisc(discDAO.buscar((Integer.parseInt(reader.readLine()))));
			aula.setProf(profDAO.buscar(Integer.parseInt(reader.readLine())));
			aula.setSala(salaDAO.buscar(Integer.parseInt(reader.readLine())));
			aula.setTurno(Turno.valueOf(reader.readLine()));
			aula.setDia(Dia.valueOf(reader.readLine()));
			try {
				data = formatador.parse(reader.readLine());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			aula.setIni(new Time(data.getTime()));
			try {
				data = formatador.parse(reader.readLine());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			aula.setFim(new Time(data.getTime()));
			reader.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return aula;
	}

	@Override
	public Persistencia atualizar(Aula e) {
		BufferedWriter writer;
		String path = Diretorios.AULA + "\\" + e.getDia() + "\\"
				+ e.getIdAula() + ".txt";
		try {
			writer = new BufferedWriter(new FileWriter(path));
			writer.write(String.valueOf(e.getIdAula()));
			writer.newLine();
			writer.write(String.valueOf(e.getDisc().getIdDisciplina()));
			writer.newLine();
			writer.write(String.valueOf(e.getProf().getId()));
			writer.newLine();
			writer.write(String.valueOf(e.getSala().getId_sala()));
			writer.newLine();
			writer.write(e.getTurno().toString());
			writer.newLine();
			writer.write(e.getDia().toString());
			writer.newLine();
			writer.write(e.getIni().toString());
			writer.newLine();
			writer.write(e.getFim().toString());
			writer.close();
			return Persistencia.GRAVADO;
		} catch (IOException e1) {
			System.err.println(e1.getMessage());
			e1.printStackTrace();
			return Persistencia.ERRO;
		}
	}

	@Override
	public boolean validaNovoRegistro(Aula novo) {
		ListaEncadeada<Aula> lista = lerTodos();
		while (lista.hasNext()) {
			if (lista.next().equals(novo))
				return false;
		}
		return true;
	}

	public ListaEncadeada<Aula> ler(Dia diaAtual){
		String path = Diretorios.AULA.toString() + "\\" + diaAtual.toString();
		File[] arquivos = new File(path).listFiles();
		Aula aula;
		FileReader fileReader;
		BufferedReader reader;
		ListaEncadeada<Aula> lista = new ListaEncadeada<Aula>();
		for(File arquivo : arquivos){
			try{
				fileReader = new FileReader(arquivo);
				reader = new BufferedReader(fileReader);
				aula = new Aula();
				aula.setIdAula(Integer.parseInt(reader.readLine()));
				aula.setDisc(discDAO.buscar((Integer.parseInt(reader.readLine()))));
				aula.setProf(profDAO.buscar(Integer.parseInt(reader.readLine())));
				aula.setSala(salaDAO.buscar(Integer.parseInt(reader.readLine())));
				aula.setTurno(Turno.valueOf(reader.readLine()));
				aula.setDia(Dia.valueOf(reader.readLine()));
				try {
					data = formatador.parse(reader.readLine());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				aula.setIni(new Time(data.getTime()));
				try {
					data = formatador.parse(reader.readLine());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				aula.setFim(new Time(data.getTime()));
				lista.addFim(aula);
				reader.close();
				fileReader.close();
			}catch(IOException e){
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return lista;
	}
}
