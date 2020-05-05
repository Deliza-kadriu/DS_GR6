import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ImportKey {

private static String checkIfItsPublicOrPrivate(Document doc) {
	doc.getDocumentElement().normalize();

	int a = doc.getElementsByTagName("RSAKeyValue").item(0).getChildNodes().getLength();
	NodeList rsa1 = doc.getElementsByTagName("RSAKeyValue");
	NodeList exp1 = doc.getElementsByTagName("Exponent");
	NodeList mod1 = doc.getElementsByTagName("Modulus");
	NodeList p1 = doc.getElementsByTagName("P");
	NodeList q1 = doc.getElementsByTagName("Q");
	NodeList dp1 = doc.getElementsByTagName("DP");
	NodeList dq1 = doc.getElementsByTagName("DQ");
	NodeList inv1 = doc.getElementsByTagName("InverseQ");
	NodeList d1 = doc.getElementsByTagName("D");
	int rsa = rsa1.getLength();
	int mod = mod1.getLength();
	int exp = exp1.getLength();
	int p = p1.getLength();
	int q = q1.getLength();
	int dp = dp1.getLength();
	int dq = dq1.getLength();
	int inv = inv1.getLength();
	int d = d1.getLength();

	if (rsa == 1 && a == 5 && mod == 1 && exp == 1 && p == 0 && q == 0 && dp == 0 && dq == 0 && inv == 0
			&& d == 0) {

		return "public";

	} else if (rsa == 1 && a == 17 && mod == 1 && exp == 1 && p == 1 && q == 1 && dp == 1 && dq == 1 && inv == 1
			&& d == 1)

		return "private";

	else

		return "error";

}
public static void import_Key(String out, String path)
		throws ParserConfigurationException, IOException, SAXException, InvalidPathException {
	try {
		if (path.matches("^(http|https|)://.*$")) {

			File freshFile = new File("keys/" + out + ".pub.xml");

			if (!freshFile.exists()) {

				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder().uri(URI.create(path)).build();
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				// System.out.println(response.body());

				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

				DocumentBuilder builder = factory.newDocumentBuilder();

				Document xmldocument = builder.newDocument();
				Element r = xmldocument.createElement("RSAKeyValue");
				r.appendChild(xmldocument.createTextNode(response.body()));
				xmldocument.appendChild(r);

				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transformer = tf.newTransformer();
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");

				DOMSource domSource = new DOMSource(xmldocument);
				StreamResult streamResult = new StreamResult(freshFile);
				transformer.transform(domSource, streamResult);
				System.out.println("Celesi publik u ruajt ne fajllin \"keys\\" + out + ".pub.xml\".");

			} else {

				System.out.println("Gabim: Celesi publik \"" + out + "\" ekziston!");

			}
		} else {

			DocumentBuilderFactory docFac = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFac.newDocumentBuilder();
			File file = new File(String.valueOf(path));

			if (file.getName().contains(".xml")) {

				if (file.exists()) {

					Document doc = docBuilder.parse(file);

					if (checkIfItsPublicOrPrivate(doc) == "public") {

						File file1 = new File("keys\\", out + ".pub.xml");
						if (file1.exists()) {

							System.out.println("Celesi '" + out + "' ekziston paraprakisht");

						} else {

							file.renameTo(file1);
							System.out.println("Celesi publik u ruajt ne fajllin \"keys\\" + out + ".pub.xml\".");

						}
					} else if (checkIfItsPublicOrPrivate(doc) == "private") {

						File file1 = new File("keys\\" + out + ".pub.xml");
						File privFile = new File("keys\\" + out + ".xml");

						if (privFile.exists()) {

							System.out.println("Celesi '" + out + "' ekziston paraprakisht");

						}

						DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

						DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

						Document document = documentBuilder.newDocument();

						Element ro = document.createElement("RSAKeyValue");

						document.appendChild(ro);
						Element modulu = document.createElement("Modulus");

						modulu.appendChild(document
								.createTextNode(doc.getElementsByTagName("Modulus").item(0).getTextContent()));
						ro.appendChild(modulu);

						Element ex = document.createElement("Exponent");

						ex.appendChild(document
								.createTextNode(doc.getElementsByTagName("Exponent").item(0).getTextContent()));
						ro.appendChild(ex);

						TransformerFactory transformerFactory = TransformerFactory.newInstance();
						Transformer transformer = transformerFactory.newTransformer();
						transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
						transformer.setOutputProperty(OutputKeys.INDENT, "yes");

						DOMSource domSource = new DOMSource(document);

						StreamResult streamResult = new StreamResult(file1);
						transformer.transform(domSource, streamResult);
						file.renameTo(privFile);

						System.out.println("Celesi privat u ruajt ne fajllin \"keys\\" + out + ".xml\".");

						System.out.println("Celesi publik u ruajt ne fajllin \"keys\\" + out + ".pub.xml\".");

					}

				} else if (!file.exists()) {

					System.out.println("File i dhene ne kete path nuk ekziston");

				}
			} else {

				System.out.println("Gabim: Fajlli i dhene nuk eshte celes valid.");

			}
		}
	} catch (Exception e) {

		e.printStackTrace();

	}

}


}
