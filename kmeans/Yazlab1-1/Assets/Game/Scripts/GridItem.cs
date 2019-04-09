using UnityEngine;
using System.Collections;

public class GridItem : MonoBehaviour {

	//Bu gridItem oyundaki her bir itemi , itemden kastettiğimizde şekerlerdir. 

	public int x {
		get;
		private set;
	}
	public int y {
		get;
		private set;
	}

	//[HideInInspector] 
	public int id; 		// her sekere ait id.s

	public void OnItemPositionChanged(int newX,int newY)
	{
		x = newX;
		y = newY;
		gameObject.name = string.Format ("Sprite [{0}][{1}]",x,y);
	}
	void OnMouseDown()
	{
		if (OnMouseOverItemEventHandler != null)
		{
			OnMouseOverItemEventHandler(this);
		}


		//Bu script'i gidip de ben prefabdaki şekere sürükleyip bıraktığımda
		//bu fonksiyon üzerine tıkladığımda bu fonksiyonu çalıştırır.
	}
	public delegate void OnMouseOverItem (GridItem item);
	//  delegate metod referansını tutar.
	// Delegate başvurduğu bir metodu dinamik olarak değiştirebilir.
	//Delegate kullanılması metodları daha rahat kontrol etmemizi sağlayacak
	//ve uygulamamızın performansını artıracaktır.
	public static event OnMouseOverItem OnMouseOverItemEventHandler;


}
