using UnityEngine;
using System.Collections;
using System.Globalization;
using System.Collections.Generic;
using System.IO;

public class SetColor
{
	public int[] renkler = new int[142];

	public List<int> kume_1 = new List<int>();
	public List<int> kume_2 = new List<int>();
	public List<int> kume_3 = new List<int>();
	
	public int k1ort, k2ort, k3ort;
	public int k1, k2, k3;
	public int eskimerkez1, eskimerkez2, eskimerkez3;
	public int sayac = 0;
	

	
	public Color FromArgb(string hexstring)
	{
		if (hexstring.StartsWith("#"))
		{
			hexstring = hexstring.Substring(1);
		}
		
		if (hexstring.StartsWith("0x"))
		{
			hexstring = hexstring.Substring(2);
		}
		while((hexstring.Length < 6))
			hexstring = "0" + hexstring;
		
		byte r = byte.Parse(hexstring.Substring(0, 2), NumberStyles.HexNumber);
		byte g = byte.Parse(hexstring.Substring(2, 2), NumberStyles.HexNumber);
		byte b = byte.Parse(hexstring.Substring(4, 2), NumberStyles.HexNumber);
		
		
		return new Color32(r, g, b, 255);
	}
	
	
	public void KmeansOnLoad()
	{
		//a1,a2,a3 değişkenlerde indislerini tuttuğumuz rastgele 3 küme merkezi oluşturur
		int a1,a2,a3;
		do
		{
			a1 = Random.Range(0, renkler.Length);
			a2 = Random.Range(0, renkler.Length);
			a3 = Random.Range(0, renkler.Length);
		} while (a1 == a2 || a1 == a3 || a2 == a3);
		
		k1 = renkler[a1];
		k2 = renkler[a2];
		k3 = renkler[a3];
		
		//tüm renkleri küme merkezlerine uzaklıklarına göre kümelere dağıtır
		for (int a = 0; a < renkler.Length; a++)
		{
			kumeleme(renkler[a]);
		}
		
		//recursive fonksiyon(metod)
		dongu();
		
	}
	
	
	
	
	
	
	
	public void dongu()
	{
		//kümelerin ortalama ve toplam değerlerini bulur 
		int toplam1 = 0, toplam2 = 0, toplam3 = 0;
		for (int i = 0; i < kume_1.Count; i++)
		{
			toplam1 = toplam1 + kume_1[i];
		}
		k1ort = toplam1 / kume_1.Count;
		
		
		
		for (int i = 0; i < kume_2.Count; i++)
		{
			toplam2 = toplam2 + kume_2[i];
		}
		k2ort = toplam2 / kume_2.Count;
		
		
		for (int i = 0; i < kume_3.Count; i++)
		{
			toplam3 = toplam3 + kume_3[i];
		}
		k3ort = toplam3 / kume_3.Count;
		
		//ortalama değere en yakın eleman kümenin yeni merkezi oluyor kısacası küme merkez kaydırma işlemi
		eskimerkez1 = k1;
		int min_fark1;
		for (int i = 0; i < kume_1.Count; i++)
		{
			min_fark1 = Mathf.Abs(k1ort - kume_1[i]);
			if (Mathf.Abs(k1 - k1ort) > min_fark1)
			{
				k1 = kume_1[i];
			}
		}
		
		
		eskimerkez2 = k2;
		int min_fark2;
		for (int i = 0; i < kume_2.Count; i++)
		{
			min_fark2 = Mathf.Abs(k2ort - kume_2[i]);
			if (Mathf.Abs(k2 - k2ort) > min_fark2)
			{
				k2 = kume_2[i];
			}
		}
		
		eskimerkez3 = k3;
		int min_fark3;
		for (int i = 0; i < kume_3.Count; i++)
		{
			min_fark3 = Mathf.Abs(k3ort - kume_3[i]);
			if (Mathf.Abs(k3 - k3ort) > min_fark3)
			{
				k3 = kume_3[i];
			}
		}





		//küme merkez kaydırma işlemi olmuyorsa eğer demekki kümeler kmeans algoritmasına uygun hale gelmiştir yani bu noktadan itibaren recursive fonksiyona daha girmez
		if (eskimerkez1 == k1 || eskimerkez2 == k2 || eskimerkez3 == k3)
		{	
			return;
		}
		else
		{
			kume_1.Clear();		
			kume_2.Clear();		
			kume_3.Clear();		
			for (int a = 0; a < renkler.Length; a++)
			{
				
				kumeleme(renkler[a]);
			}
		
			sayac++;
			dongu();
		}
	}

	
	public void kumeleme(int eleman)
	{
		int fark1, fark2, fark3;
		fark1 = Mathf.Abs(k1 - eleman);
		fark2 = Mathf.Abs(k2 - eleman);
		fark3 = Mathf.Abs(k3 - eleman);
		if (fark1 < fark2 && fark1 < fark3)
		{
			kume_1.Add(eleman);	
		
		}
		else if (fark2 < fark1 && fark2 < fark3)
		{
			kume_2.Add (eleman);

		}
		else if (fark3 < fark2 && fark3 < fark1)
		{
			kume_3.Add(eleman);

		}
	}

	
	public void getColor()
	{

		
		StreamReader oku;
		oku = File.OpenText("renkler.txt");
		string str2 = oku.ReadLine();
		int g = 0;
		ArrayList renk2 = new ArrayList();
		while ((str2 = oku.ReadLine())!=null)
		{
			renk2.Add(str2);
			
			g++;
		}
		
		
		for (int i = 0; i < renk2.Count; i++)
		{
			string hexString = renk2[i].ToString();
			renkler[i] = int.Parse(hexString, System.Globalization.NumberStyles.HexNumber);
		}
		oku.Close();
	
	}
	
	
	
	
	
	
}
