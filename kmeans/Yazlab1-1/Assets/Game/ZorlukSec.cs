using UnityEngine;
using System.Collections;

public class ZorlukSec : MonoBehaviour
{
	public Texture bg;



	public void OnGUI()
	{
		GUI.DrawTexture (new Rect(0,0,Screen.width,Screen.height),bg);

		if(GUI.Button (new Rect(Screen.width * .50f,Screen.height * .35f,Screen.width * .1f,Screen.height * .1f),"KOLAY"))
		{
			Application.LoadLevel(2);
		}
		if(GUI.Button (new Rect(Screen.width * .50f,Screen.height * .50f,Screen.width * .1f,Screen.height * .1f),"ORTA"))
		{
			Application.LoadLevel(3);
		}
		if(GUI.Button (new Rect(Screen.width * .50f,Screen.height * .65f,Screen.width * .1f,Screen.height * .1f),"ZOR"))
		{
			Application.LoadLevel(4);
		}
		if(GUI.Button (new Rect(Screen.width * .80f,Screen.height * .80f,Screen.width * .1f,Screen.height * .1f),"ÇIKIŞ"))
		{
			Application.Quit();
		}

	}




}
