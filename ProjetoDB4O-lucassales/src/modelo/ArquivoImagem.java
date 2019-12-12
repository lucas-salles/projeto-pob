package modelo;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import dao.IDInterface;

public class ArquivoImagem implements IDInterface{
	private int id;				//autoincrementado
	private String nome;		//nome do arquivo lido
	private byte[] bytesimagem;	//bytes do arquivo

	public ArquivoImagem(){}

	public ArquivoImagem(BufferedImage bf, String nome) {
		super();
		this.nome = nome;
		setBufferedImage(bf);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BufferedImage getBufferedImage() throws Exception{
		try {
			InputStream in = new ByteArrayInputStream(this.bytesimagem);
			BufferedImage bf = ImageIO.read(in);
			return bf;
		} catch (IOException e) {
			throw new Exception("leitura de arquivo invalida");
		}
		
	}

	public void setBufferedImage(BufferedImage bf) {
		try{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bf, "jpg", baos );		// tipo de gravacao
			baos.flush();
			baos.close();
			this.bytesimagem = baos.toByteArray();		
		}
		catch(Exception e ){
			e.printStackTrace();
		}

	}


}
