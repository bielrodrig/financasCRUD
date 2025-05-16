package face;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class face {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Caminho para o classificador HaarCascade
        String xmlFile = "C:\\opencv\\build\\etc\\haarcascades\\haarcascade_frontalface_alt.xml";
        CascadeClassifier faceDetector = new CascadeClassifier(xmlFile);

        // Abre a câmera
        VideoCapture camera = new VideoCapture(0);
        if (!camera.isOpened()) {
            System.out.println("Erro ao abrir a câmera");
            return;
        }

        // Captura um frame para saber o tamanho da imagem
        Mat frame = new Mat();
        camera.read(frame); // pega o primeiro frame
        int width = frame.width();
        int height = frame.height();

        // Cria a janela com JLabel para mostrar o vídeo
        JFrame frameWindow = new JFrame("Detecção Facial");
        JLabel imageLabel = new JLabel();
        frameWindow.getContentPane().add(imageLabel);
        frameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameWindow.pack();
        frameWindow.setVisible(true);

        Mat gray = new Mat();

        // Loop infinito para capturar a câmera em tempo real
        while (true) {
            if (camera.read(frame)) {
                // Converte para escala de cinza
                Imgproc.cvtColor(frame, gray, Imgproc.COLOR_BGR2GRAY);

                // Detecta rostos
                MatOfRect faces = new MatOfRect();
                faceDetector.detectMultiScale(gray, faces, 1.1, 4,
                        0, new Size(100, 100), new Size());

                for (Rect rect : faces.toArray()) {
                    Imgproc.rectangle(frame, new Point(rect.x, rect.y),
                            new Point(rect.x + rect.width, rect.y + rect.height),
                            new Scalar(0, 255, 0), 2);
                }

                // Converte para BufferedImage e exibe
                ImageIcon icon = new ImageIcon(matToBufferedImage(frame));
                imageLabel.setIcon(icon);
                frameWindow.pack(); // garante que o JLabel se ajuste ao novo frame
                imageLabel.repaint();

                System.out.println("Faces detectadas: " + faces.toArray().length);
            }
        }
    }

    // Converte Mat para BufferedImage sem distorção
    private static BufferedImage matToBufferedImage(Mat matrix) {
        int width = matrix.width();
        int height = matrix.height();
        int channels = matrix.channels();

        byte[] sourcePixels = new byte[width * height * channels];
        matrix.get(0, 0, sourcePixels);

        // BufferedImage que vamos criar
        BufferedImage image;

        if (channels == 3) {
            // Criar BufferedImage RGB
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            int[] pixels = new int[width * height];

            // Converter BGR (OpenCV) para RGB (BufferedImage)
            for (int i = 0; i < width * height; i++) {
                int blue = sourcePixels[i * 3] & 0xFF;
                int green = sourcePixels[i * 3 + 1] & 0xFF;
                int red = sourcePixels[i * 3 + 2] & 0xFF;

                pixels[i] = (red << 16) | (green << 8) | blue;
            }

            image.setRGB(0, 0, width, height, pixels, 0, width);

        } else if (channels == 1) {
            // Imagem em escala de cinza
            image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
            image.getRaster().setDataElements(0, 0, width, height, sourcePixels);
        } else {
            throw new IllegalArgumentException("Número de canais da imagem não suportado: " + channels);
        }

        return image;
    }
}
