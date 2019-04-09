using UnityEngine;
using System.Collections;

public class OyunArkaPlan : MonoBehaviour {

	public Texture bg;

	public void OnGUI()
	{
		GUI.DrawTexture (new Rect(0,0,Screen.width,Screen.height),bg);
	}

}
