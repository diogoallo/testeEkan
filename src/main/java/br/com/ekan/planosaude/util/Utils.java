package br.com.ekan.planosaude.util;

import org.springframework.stereotype.Controller;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;

@Controller
public class Utils {

	private static final String maskDATE = "dd/MM/yyyy";

	public static String FormataData(java.util.Date data) {
		try {
			DateFormat formatter = new SimpleDateFormat(maskDATE);
			return formatter.format(data);
		} catch (Exception ex) {
			return "-";
		}
	}


}

