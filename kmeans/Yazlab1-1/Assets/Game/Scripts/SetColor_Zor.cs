using UnityEngine;
using System.Collections;
using System.Globalization;
using System.Collections.Generic;
using System.IO;
public class SetColor_Zor 
{

	public int[] renkler=new int[142];
	public List<int> kume_1 = new List<int>();
	public List<int> kume_2 = new List<int>();
	public List<int> kume_3 = new List<int>();
	public List<int> kume_4 = new List<int>();
	public List<int> kume_5 = new List<int>();

	public int k1ort , k2ort , k3ort , k4ort,k5ort;
	public int k1,k2,k3,k4,k5;
	public int eskimerkez1,eskimerkez2,eskimerkez3,eskimerkez4,eskimerkez5;
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
		int[] a = new int[5];
		
		
		bool durum = false;
		do
		{
			a[0] = Random.Range(0, renkler.Length);
			a[1] = Random.Range(0, renkler.Length);
			a[2] = Random.Range(0, renkler.Length);
			a[3] = Random.Range(0, renkler.Length);
			a[4] = Random.Range(0, renkler.Length);
			
			for (int i = 0; i < 5; i++)
			{
				for (int j = 1; j < 5; j++)
				{
					if (a[i] == a[j] &&  i != j)
					{
						durum=true;
					}	
				}	
			}
			
			
		} while (durum);
		
		k1 = renkler[a[0]];
		k2 = renkler[a[1]];
		k3 = renkler[a[2]];
		k4 = renkler[a[3]];
		k5 = renkler[a[4]];
		
		for (int i = 0; i < renkler.Length; i++)
		{
			kumeleme(renkler[i]);
		}
		
		
		dongu();
		
	}

	
	public void kumeleme(int eleman)
	{
		int fark1, fark2, fark3,fark4,fark5;
		fark1 = Mathf.Abs(k1 - eleman);
		fark2 = Mathf.Abs(k2 - eleman);
		fark3 = Mathf.Abs(k3 - eleman);
		fark4 = Mathf.Abs(k4 - eleman);
		fark5 = Mathf.Abs(k5 - eleman);
		if (fark1 < fark2 && fark1 < fark3 && fark1 < fark4 && fark1 < fark5)
		{
			kume_1.Add(eleman);	
			
		}
		else if (fark2 < fark1 && fark2 < fark3 && fark2< fark4 && fark2 < fark5)
		{
			kume_2.Add (eleman);
			
		}
		else if (fark3 < fark2 && fark3 < fark1 && fark3 < fark4 && fark3 < fark5)
		{
			kume_3.Add(eleman);
			
		}
		else if(fark4 < fark1 && fark4 < fark2 && fark4 < fark3 && fark4 < fark5)
		{
			kume_4.Add(eleman);
		}
		else if(fark5 < fark1 && fark5 < fark2 && fark5 < fark3 && fark5 < fark4)
		{
			kume_5.Add(eleman);
		}
	}
	
	
	public void dongu()
	{
		int toplam1 = 0, toplam2 = 0, toplam3 = 0,toplam4 = 0,toplam5=0;
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
		
		for (int i = 0; i < kume_4.Count; i++)
		{
			toplam4 = toplam4 + kume_4[i];
		}
		k4ort = toplam4 / kume_4.Count;
		
		for (int i = 0; i < kume_5.Count; i++)
		{
			toplam5 = toplam5 + kume_5[i];
		}
		k5ort = toplam5 / kume_5.Count;
		

		
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
		
		eskimerkez4 = k4;
		int min_fark4;
		for (int i = 0; i < kume_4.Count; i++)
		{
			min_fark4 = Mathf.Abs(k4ort - kume_4[i]);
			if (Mathf.Abs(k4 - k4ort) > min_fark4)
			{
				k4 = kume_4[i];
			}
		}

		eskimerkez5 = k5;
		int min_fark5;
		for (int i = 0; i < kume_5.Count; i++)
		{
			min_fark5 = Mathf.Abs(k4ort - kume_5[i]);
			if (Mathf.Abs(k5 - k5ort) > min_fark5)
			{
				k5 = kume_5[i];
			}
		}
	
	
	}

	public void getColor()
	{
		
		StreamReader oku;
		oku = File.OpenText("renkler.txt");
		string str2 = oku.ReadLine();
		int g = 0;
		ArrayList renk = new ArrayList();
		while ((str2 = oku.ReadLine())!=null)
		{
			renk.Add(str2);
			
			g++;
		}
		
		
		for (int i = 0; i < renk.Count; i++)
		{
			string hexString = renk[i].ToString();
			renkler[i] = int.Parse(hexString, System.Globalization.NumberStyles.HexNumber);
		}
		
		oku.Close();
		
	}
	
	public int txtSatir()
	{
		StreamReader oku;
		oku = File.OpenText("renkler.txt");
		string str2 = oku.ReadLine();
		int sayac = 0;
		while ((str2 = oku.ReadLine())!=null)
		{	
			sayac++;
		}
		
		oku.Close();
		return sayac;
		
	}


}
