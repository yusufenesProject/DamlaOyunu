using UnityEngine;
using System.Collections;
using System.Collections.Generic;


public class GameGrid : MonoBehaviour {

	public int xSize,ySize;
	public float spr_width=0.90f;
	private GameObject[] _candies;
	private GridItem[,] _items;
	private GridItem _currentlySelectedItem;
	public static int minItemsForMatch = 3;		// en az kaç topta patlama olmalı ? 
	public float delayBetweenMatches =0.1f;
	public bool canPlay;
	public SetColor chooseColor = new SetColor();
	public SetColor_Orta chooseColor_orta = new SetColor_Orta();
	public SetColor_Zor chooseColor_zor = new SetColor_Zor();
	public int zorluk;

	 

	void Start () 
	{	

		if(zorluk == 1)
		{
		chooseColor.getColor();
		chooseColor.KmeansOnLoad();
		}
		else if(zorluk == 2 )
		{

			chooseColor_orta.getColor();
			chooseColor_orta.KmeansOnLoad();
		}
		else if (zorluk == 3)
		{
			chooseColor_zor.getColor();
			chooseColor_zor.KmeansOnLoad();
		}

		canPlay=true;
		GetCandies ();
		FillGrid ();
		ClearGrid();
		GridItem.OnMouseOverItemEventHandler += OnMouseOverItem;
		/*
		Destroy (_items[0,6].gameObject);
			Bu kod ile matris mantığında yaptığımız şekerlerde	
	0,6 yı yok edecektir.
		*/
	}


	void OnDisable ()
	{
		GridItem.OnMouseOverItemEventHandler -= OnMouseOverItem;
	}


	void FillGrid ()
	{
		_items = new GridItem[xSize,ySize];

		for (int x = 0; x < xSize; x++)
		{
			for (int y = 0; y < ySize; y++)
			{
					// sddsfs
				_items[x,y]= InstantiateCandy(x,y);
			}
		}
	}

	void ClearGrid()
	{
		for (int x = 0; x < xSize; x++)
		{
			for (int y = 0; y < ySize; y++) 
			{
				MatchInfo matchInfo = GetMatchInformation(_items[x,y]);
				if (matchInfo.validMatch) 
				{
					Destroy(_items [x,y].gameObject);	
					_items[x,y] = InstantiateCandy(x,y);
					y--;  
				}
			}	
		}
	}


	GridItem InstantiateCandy (int x,int y) 
	{
		GameObject randomCandy = _candies[Random.Range(0,_candies.Length)];
		GridItem newCandy = ((GameObject)Instantiate (randomCandy, new Vector3 (x*spr_width, y), Quaternion.identity)).GetComponent<GridItem>();         
		newCandy.OnItemPositionChanged (x,y);
		if(zorluk==1)
		{
			switch (newCandy.id)
			{

			case 0 :
					string clr = chooseColor.kume_1[Random.Range(0,chooseColor.kume_1.Count)].ToString("X");
					newCandy.GetComponent<SpriteRenderer>().color = chooseColor.FromArgb(clr);

			//	newCandy.GetComponent<SpriteRenderer>().color = Color.black;
				break;
			case 1 :
				string clr2 = chooseColor.kume_2[Random.Range(0,chooseColor.kume_2.Count)].ToString("X");
				newCandy.GetComponent<SpriteRenderer>().color = chooseColor.FromArgb(clr2);

				break;
			case 2 :
				string clr3 = chooseColor.kume_3[Random.Range(0,chooseColor.kume_3.Count)].ToString("X");
				newCandy.GetComponent<SpriteRenderer>().color = chooseColor.FromArgb(clr3);

				break;
			default:
				break;
			}
		}
		else if (zorluk==2)
		{
			switch (newCandy.id)
			{
				
			case 0 :
				string clr = chooseColor_orta.kume_1[Random.Range(0,chooseColor_orta.kume_1.Count)].ToString("X");
				newCandy.GetComponent<SpriteRenderer>().color = chooseColor_orta.FromArgb(clr);
				
				//	newCandy.GetComponent<SpriteRenderer>().color = Color.black;
				break;
			case 1 :
				string clr2 = chooseColor_orta.kume_2[Random.Range(0,chooseColor_orta.kume_2.Count)].ToString("X");
				newCandy.GetComponent<SpriteRenderer>().color = chooseColor_orta.FromArgb(clr2);
				
				break;
			case 2 :
				string clr3 = chooseColor_orta.kume_3[Random.Range(0,chooseColor_orta.kume_3.Count)].ToString("X");
				newCandy.GetComponent<SpriteRenderer>().color = chooseColor_orta.FromArgb(clr3);
				
				break;
			case 3 :
				string clr4 = chooseColor_orta.kume_4[Random.Range(0,chooseColor_orta.kume_4.Count)].ToString("X");
				newCandy.GetComponent<SpriteRenderer>().color = chooseColor_orta.FromArgb(clr4);
				
				break;
				
			default:
				break;
			}
		}
		if(zorluk==3)
		{
			switch (newCandy.id)
			{
				
			case 0 :
				string clr = chooseColor_zor.kume_1[Random.Range(0,chooseColor_zor.kume_1.Count)].ToString("X");
				newCandy.GetComponent<SpriteRenderer>().color = chooseColor_zor.FromArgb(clr);
				
				//	newCandy.GetComponent<SpriteRenderer>().color = Color.black;
				break;
			case 1 :
				string clr2 = chooseColor_zor.kume_2[Random.Range(0,chooseColor_zor.kume_2.Count)].ToString("X");
				newCandy.GetComponent<SpriteRenderer>().color = chooseColor_zor.FromArgb(clr2);
				
				break;
			case 2 :
				string clr3 = chooseColor_zor.kume_3[Random.Range(0,chooseColor_zor.kume_3.Count)].ToString("X");
				newCandy.GetComponent<SpriteRenderer>().color = chooseColor_zor.FromArgb(clr3);
				
				break;
			case 3 :
				string clr4 = chooseColor_zor.kume_4[Random.Range(0,chooseColor_zor.kume_4.Count)].ToString("X");
				newCandy.GetComponent<SpriteRenderer>().color = chooseColor_zor.FromArgb(clr4);
				
				break;
			case 4 :
				/*string clr5 = chooseColor_zor.kume_5[Random.Range(0,chooseColor_zor.kume_5.Count)].ToString("X");
				newCandy.GetComponent<SpriteRenderer>().color = chooseColor_zor.FromArgb(clr5);
				*/
				newCandy.GetComponent<SpriteRenderer>().color= Color.black;
				break;
			default:
				break;
			}
		}





		return newCandy;
	}
	

	#region onmouseoveritem
	void OnMouseOverItem( GridItem item)
	{
		if (_currentlySelectedItem == item || !canPlay) 
		{
			return;
			//Bir önce seçtiğim şekerle bu şeker aynıysa birşey yapma.
		}

		if (_currentlySelectedItem == null) {
			_currentlySelectedItem = item;
		} 
		else 
		{
			// Bu kısımda şunu yaptık hiç şeker seçmemişsem bu zamana kadar, ilk seçeceğim griditem
			// currentlySelectedItem'a gidicektir.
			//İkinci seçeceğim gridItem'dan sonra şunu kontrol ederiz.Kordinat düzlemindeki mesafe farkına barız.

			float xDiff = Mathf.Abs(item.x-_currentlySelectedItem.x);
			float yDiff = Mathf.Abs (item.y-_currentlySelectedItem.y);

			if(xDiff+yDiff == 1)
			{
				//Şekerler arasında yer değişimine izin vardır.
				StartCoroutine(TryMatch (_currentlySelectedItem,item));
			}
			else
			{
				//Şekerlerin yer değiştirmesine izin yoktur.
			}

			_currentlySelectedItem=null;
		}
	}
	#endregion

	IEnumerator TryMatch (GridItem a,GridItem b)
	{
		canPlay=false;
		yield return StartCoroutine (Swap(a,b));

		MatchInfo matchA = GetMatchInformation (a);
		MatchInfo matchB = GetMatchInformation (b);   


		if (!matchA.validMatch && !matchB.validMatch) 
		{
			canPlay=true;
			yield return StartCoroutine (Swap(a,b));
			yield break;
		}

		if (matchA.validMatch)  
		{
			yield return StartCoroutine (DestroyItems(matchA.match));
			yield return new WaitForSeconds (delayBetweenMatches);
			yield return StartCoroutine (UpdateGridAfterMatch(matchA));
		}
		else if(matchB.validMatch)
		{
			yield return StartCoroutine (DestroyItems(matchB.match));
			yield return new WaitForSeconds(delayBetweenMatches);
			yield return StartCoroutine(UpdateGridAfterMatch(matchB));
		}
		canPlay=true;
	}

	IEnumerator UpdateGridAfterMatch(MatchInfo match)
	{

		if(match.matchStartingY == match.matchEndingY)
		{
			for(int x = match.matchStartingX; x <= match.matchEndingX; x++)
			{
				for(int y= match.matchStartingY; y < ySize-1; y++)
				{
					GridItem upperIndex = _items[x,y+1];
					GridItem current = _items[x,y];
					_items [x,y] = upperIndex;
					_items [x,y + 1] = current;
					_items [x,y].OnItemPositionChanged(_items [x,y].x,_items [x,y].y-1);
				}
			_items[x,ySize-1] = InstantiateCandy(x,ySize-1);
			}

		}
		else if(match.matchEndingX == match.matchStartingX)
		{
			int matchHeight = 1 + (match.matchEndingY-match.matchStartingY);
			for(int y = match.matchStartingY + matchHeight; y <= ySize - 1; y++)
			{
				GridItem lowerIndex = _items[match.matchStartingX,y-matchHeight];
				GridItem current = _items[match.matchStartingX,y];
				_items [match.matchStartingX , y - matchHeight] = current;
				_items [match.matchStartingX,y] = lowerIndex;
			}

			for(int y =0;y < ySize - matchHeight; y++)
			{
				_items[match.matchStartingX,y].OnItemPositionChanged(match.matchStartingX,y);
		
			}

			for(int i=0; i < match.match.Count; i++)
			{
				_items [match.matchStartingX,(ySize-1)-i] = InstantiateCandy(match.matchStartingX,(ySize-1)-i);
			}

		}
		for (int x = 0; x < xSize; x++)
		{
			for (int y = 0; y < ySize; y++) 
			{
				MatchInfo matchInfo = GetMatchInformation(_items[x,y]);
				if (matchInfo.validMatch) 
				{
					//yield return new WaitForSeconds(delayBetweenMatches);
					yield return StartCoroutine(DestroyItems (matchInfo.match));
					yield return new WaitForSeconds (delayBetweenMatches);
					yield return StartCoroutine (UpdateGridAfterMatch(matchInfo));
				}
			}	
		}
	}




	IEnumerator DestroyItems (List<GridItem> items)
	{

		foreach (GridItem g in items) 
		{
			yield return StartCoroutine(g.transform.Scale(Vector3.zero,0.15f));
			Destroy (g.gameObject);
		}

	}


	IEnumerator Swap (GridItem a, GridItem b)
	{
	//	ChangeRigidbodyStatus (false);
				// yer değişikliğinden önce hepsinin yerini sabitleyelim ve yerçekimini iptal edelim.

		float movDuration=0.1f;
		Vector3 aPosition = a.transform.position;		//geçici 
		StartCoroutine(a.transform.Move(b.transform.position,movDuration));
		StartCoroutine (b.transform.Move (aPosition, movDuration));
		yield return new WaitForSeconds (movDuration);
		SwapIndices (a,b);
	//	ChangeRigidbodyStatus (true);

	}

	void SwapIndices(GridItem a,GridItem b)
	{ 
		GridItem tempA = _items[a.x,a.y];	// geçici
		_items [a.x, a.y] = b;
		_items [b.x, b.y] = tempA;

		int bOldX = b.x;
		int bOldY = b.y;
		b.OnItemPositionChanged (a.x, a.y);
		a.OnItemPositionChanged (bOldX,bOldY);
	}

	List<GridItem> SearchHorizantally (GridItem item)
	{
		List<GridItem> hItems = new List<GridItem>{ item };
		int left = item.x - 1;
		int right = item.x + 1;
		while (left>=0 && _items[left,item.y].id == item.id) 	//id ler burda aynı renki temsil ediyor sanırım.
		{
			hItems.Add(_items[left,item.y]);
			left--;   
		}
		while (right< xSize && _items[right,item.y].id==item.id)
		{
			hItems.Add(_items[right,item.y]);
			right++;
		}
		return hItems;
	}

	List<GridItem> SearchVertically(GridItem item)
	{
		List<GridItem> vItems = new List<GridItem> {item };
		int up = item.y + 1;
		int down = item.y - 1;

		while (down >= 0 && _items[item.x,down].id== item.id )
		{
			vItems.Add(_items[item.x,down]);
			down--;
		}
		while (up < ySize && _items[item.x,up].id==item.id) 
		{
			vItems.Add(_items[item.x,up]);
			up++;
		}
		return vItems;
	}

	MatchInfo GetMatchInformation(GridItem item)
	{
		MatchInfo m = new MatchInfo ();
		m.match = null;
		List<GridItem> hMatch = SearchHorizantally(item);
		List<GridItem> vMatch = SearchVertically (item);

		if (hMatch.Count >= minItemsForMatch && hMatch.Count > vMatch.Count) 
		{
			m.matchStartingX = GetMinimumX(hMatch);
			m.matchEndingX= GetMaximumX(hMatch);
			m.matchStartingY = m.matchEndingY = hMatch[0].y;
			m.match = hMatch; 

		} 
		else if (vMatch.Count >= minItemsForMatch) 
		{
			m.matchStartingY = GetMinimumY(vMatch);
			m.matchEndingY = GetMaximumY(vMatch);
			m.matchStartingX = m.matchEndingX = vMatch[0].x;
			m.match = vMatch;
		}
		return m;
	}

	int GetMinimumX (List<GridItem> items)
	{
		float[] indisler = new float[items.Count];
		for (int i = 0; i < indisler.Length; i++) 
		{
			indisler[i] = items[i].x;
		}
		return (int)Mathf.Min (indisler);
	}
	int GetMaximumX (List<GridItem> items)
	{
		float[] indisler = new float[items.Count];
		for (int i = 0; i < indisler.Length; i++) 
		{
			indisler[i] = items[i].x;
		}
		return (int)Mathf.Max (indisler);
	}
	int GetMinimumY (List<GridItem> items)
	{
		float[] indisler = new float[items.Count];
		for (int i = 0; i < indisler.Length; i++) 
		{
			indisler[i] = items[i].y;
		}
		return (int)Mathf.Min (indisler);
	}
	int GetMaximumY (List<GridItem> items)
	{
		float[] indisler = new float[items.Count];
		for (int i = 0; i < indisler.Length; i++) 
		{
			indisler[i] = items[i].y;
		}
		return (int)Mathf.Max (indisler);
	}


	void GetCandies ()
	{
		if(zorluk == 1)
		{
			_candies = Resources.LoadAll <GameObject> ("Prefabs");
			for (int i = 0; i < _candies.Length; i++)
			{
				_candies[i].GetComponent <GridItem>().id =i;
				// _candies gameobject dizisi,biz buna griditemdan bir bileşen veriyoruz.Burda id'yi ekledik. 
			}
		}
		else if (zorluk == 2)
		{
			_candies = Resources.LoadAll <GameObject> ("Prefabs2");
			for (int i = 0; i < _candies.Length; i++)
			{
				_candies[i].GetComponent <GridItem>().id =i;
				// _candies gameobject dizisi,biz buna griditemdan bir bileşen veriyoruz.Burda id'yi ekledik. 
			}

		}
		else if (zorluk == 3)
		{
			_candies = Resources.LoadAll <GameObject> ("Prefabs3");
			for (int i = 0; i < _candies.Length; i++)
			{
				_candies[i].GetComponent <GridItem>().id =i;
				// _candies gameobject dizisi,biz buna griditemdan bir bileşen veriyoruz.Burda id'yi ekledik. 
			}


		}

	}

	void ChangeRigidbodyStatus ( bool status)
	{
		foreach (GridItem g_item in _items) 
		{
			g_item.GetComponent<Rigidbody2D>().isKinematic = !status;
		}
	}



}
