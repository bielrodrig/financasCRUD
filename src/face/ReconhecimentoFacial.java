package face;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class ReconhecimentoFacial {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public byte[] capturarComInterface() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        CascadeClassifier detectorFace = new CascadeClassifier("C:\\opencv\\build\\etc\\haarcascades\\haarcascade_frontalface_alt.xml");
        Mat frame = new Mat();
        Mat cinza = new Mat();

        VideoCapture camera = new VideoCapture(0);
        if (!camera.isOpened()) {
            JOptionPane.showMessageDialog(null, "Não foi possível abrir a câmera.");
            return null;
        }

        while (!camera.read(frame)) {
            Thread.yield();
        }

        JFrame janela = new JFrame("Captura Facial");
        JLabel painel = new JLabel();
        janela.getContentPane().add(painel);
        janela.setSize(640, 480);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setVisible(true);


        byte[] imagemCapturada = null;
        boolean capturou = false;


        while (!capturou && janela.isVisible()) {
            if (!camera.read(frame)) continue;

            Imgproc.cvtColor(frame, cinza, Imgproc.COLOR_BGR2GRAY);
            MatOfRect rostos = new MatOfRect();
            detectorFace.detectMultiScale(cinza, rostos, 1.1, 5);

            for (Rect rosto : rostos.toArray()) {
                Imgproc.rectangle(frame, new Point(rosto.x, rosto.y), new Point(rosto.x + rosto.width, rosto.y + rosto.height), new Scalar(0, 255, 0), 2);

                Mat faceMat = new Mat(frame, rosto);
                MatOfByte buffer = new MatOfByte();
                Imgcodecs.imencode(".png", faceMat, buffer);
                imagemCapturada = buffer.toArray();
                capturou = true;
                break;
            }

            BufferedImage img = matToBufferedImage(frame);
            painel.setIcon(new ImageIcon(img));
            painel.repaint();

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                break;
            }
        }

        camera.release();
        janela.dispose();
        return imagemCapturada;
    }

    private BufferedImage matToBufferedImage(Mat mat) {
        int tipo = (mat.channels() == 1) ? BufferedImage.TYPE_BYTE_GRAY : BufferedImage.TYPE_3BYTE_BGR;
        BufferedImage imagem = new BufferedImage(mat.width(), mat.height(), tipo);
        byte[] dados = ((DataBufferByte) imagem.getRaster().getDataBuffer()).getData();
        mat.get(0, 0, dados);
        return imagem;
    }
}
