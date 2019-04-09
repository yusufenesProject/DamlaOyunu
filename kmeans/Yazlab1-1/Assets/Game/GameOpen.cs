using UnityEngine;
using System.Collections;

public class GameOpen : MonoBehaviour {

	public Texture bg;
	

	public void OnGUI()
	{
	GUI.DrawTexture (new Rect(0,0,Screen.width,Screen.height),bg);

		if(GUI.Button (new Rect(Screen.width * .37f,Screen.height * .50f,Screen.width * .1f,Screen.height * .1f),"Oyna"))
		{			
			Application.LoadLevel(1);
		}
		if(GUI.Button (new Rect(Screen.width * .53f,Screen.height * .50f,Screen.width * .1f,Screen.height * .1f),"Çıkış"))
		{			

			Application.Quit();		//Çıkış
		}

	}

}
