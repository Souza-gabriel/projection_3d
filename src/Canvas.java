import ObjectReader.ObjectLoarder;
import Vectors.Form;
import Vectors.Vector3D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Canvas extends JPanel implements Runnable{
	Thread runner;
	boolean ativo = true;
	
	private BufferedImage imageBuffer;
	private byte[] bufferDeVideo;
	private Paleta cor = new Paleta();
	private final Algorithms alg;
	private List<Form> formList;
	private Mouse mouse;
	private Keyboard keyboard;

	public Canvas(Mouse mouse, Keyboard keyboard, Algorithms alg) {
		setSize(Configs.WIDTH, Configs.HEIGHT);
		imageBuffer = new BufferedImage(Configs.WIDTH, Configs.HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
		bufferDeVideo = ((DataBufferByte)imageBuffer.getRaster().getDataBuffer()).getData();

		addMouseListener(mouse.getClickListner());
		addMouseMotionListener(mouse.getCursorListener());
		ObjectLoarder ol = new ObjectLoarder();
		ol.parseObjectFile("./src/resources/t_34_obj.obj");
		formList = ol.getForm();

		this.keyboard = keyboard;
		this.alg = alg;
		this.alg.setCanvas(this);
		this.mouse = mouse;
		System.out.println("TESTE");
		for (Form f : formList){
			for (int i = 0; i < f.getVector3D().size(); i++){
				f.getVector3D().set(i, alg.setScale(8,8,8).multiplyVector3D(f.getVector3D().get(i)));
			}
			for (int i = 0; i < f.getVector3D().size(); i++){
				f.getVector3D().set(i, alg.setRotate3D(100).multiplyVector3D(f.getVector3D().get(i)));
			}
			for (int i = 0; i < f.getVector3D().size(); i++){
				f.getVector3D().set(i, alg.setTranslate3D(300,300,100).multiplyVector3D(f.getVector3D().get(i)));
			}

		}
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		alg.drawForms(formList);

		g.setColor(Color.white);
		g.fillRect(0, 0, Configs.WIDTH, Configs.HEIGHT);
		g.setFont(Configs.FONT);

		g.drawImage(imageBuffer,0,0,null);

		g.setColor(Color.black);

	}
	
	public void simulaMundo(){
		for(int i = 0; i < bufferDeVideo.length;i++) {
			bufferDeVideo[i] = (byte)0xff;
		}
		if(mouse.isClicked()){
			for (Form f : formList){
				for (int i = 0; i < f.getVector3D().size(); i++){
					f.getVector3D().set(i, alg.setRotate3D(2).multiplyVector3D(f.getVector3D().get(i)));
				}
			}
		}
		if(keyboard.isMoving()){
			for (Form f : formList){
				for (int i = 0; i < f.getVector3D().size(); i++){
					f.getVector3D().set(i, keyboard.getTranslateMatriz().multiplyVector3D(f.getVector3D().get(i)));
				}
			}
			keyboard.setMoving(false);
		}
	}

	@Override
	public void run() {

		while(ativo){
			simulaMundo();
			paintImmediately(0, 0, Configs.WIDTH, Configs.HEIGHT); // apresenta o canvas

			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void start(){
		runner = new Thread(this);
		runner.start();
	}

	public Paleta getCor() {
		return cor;
	}

	public BufferedImage getImageBuffer() {
		return imageBuffer;
	}

	public byte[] getBufferDeVideo() {
		return bufferDeVideo;
	}

	public void setCor(Paleta cor) {
		this.cor = cor;
	}


	public void setImageBuffer(BufferedImage imageBuffer) {
		this.imageBuffer = imageBuffer;
	}

	public void setBufferDeVideo(byte[] bufferDeVideo) {
		this.bufferDeVideo = bufferDeVideo;
	}
}
