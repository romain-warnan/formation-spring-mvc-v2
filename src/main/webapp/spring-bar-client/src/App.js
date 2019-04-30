import React from 'react'
import './App.css'

class App extends React.Component {
	
	constructor(props) {
		super(props)
		this.state = {
			filteredCocktails: [],
			selectedCocktails: [],
		}
		this.handleChange = this.handleChange.bind(this)
		this.handleClick = this.handleClick.bind(this)
	}
	
	handleChange(event) {
		const q = event.target.value
		const url = new URL('http://localhost:8080/cocktails/search')
		url.search = new URLSearchParams({ q })  
		fetch(url).then(response => {
			if(response.ok) {
			    response.json().then(cocktails => {
						this.setState({ filteredCocktails: cocktails })
					})
			  }
		})
	}

	handleClick(event) {
		const cocktailId = event.target.value
		const { selectedCocktails } = this.state
		fetch('http://localhost:8080/cocktails/' + cocktailId).then(response => {
			if(response.ok) {
			    response.json().then(cocktail => {
						selectedCocktails.push(cocktail)
					})
			  }
		})
		this.setState({ selectedCocktails })
	}
	
  render(){
	  const {filteredCocktails, selectedCocktails} = this.state
	  return (
				<div>
					<p>
						<input type="text" onChange={this.handleChange} />
					</p>
					<p>{
						filteredCocktails.length > 0 ?
						<select multiple="multiple" size={Math.min(filteredCocktails.length, 10)}>
							{filteredCocktails.map(cocktail => <option value={cocktail.id} onClick={this.handleClick}>{cocktail.nom}</option>)}
						</select>
						: <br/>
						}</p>
					<p>{
						selectedCocktails.length > 0 ?
						<table>
						<tr>
							<th>Id</th>
							<th>Nom</th>
							<th>Prix</th>
						</tr>
						{selectedCocktails.map((cocktail, index) =>
							<tr key={index}>
								<td>{cocktail.id}</td>
								<td>{cocktail.nom}</td>
								<td>{cocktail.prix}</td>
							</tr>
						)}
						</table>
						: <br/>
					}</p>
				</div>
		  )
  }
}

export default App