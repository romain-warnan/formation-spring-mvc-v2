import React from 'react'
import './App.css'
import SearchBar from './SearchBar';
import CocktailsTable from './CocktailsTable';

class Commande extends React.Component {
	
	constructor(props) {
		super(props)
		this.state = {
			filteredCocktails: [],
			selectedCocktails: [],
			order: {},
		}
	}
	
	selectCocktail = cocktail => () => {
		const { selectedCocktails} = this.state
		selectedCocktails.push(cocktail)
		this.setState({ selectedCocktails })
	}
	
	removeCocktail = index => {
		const { selectedCocktails} = this.state
		selectedCocktails.splice(index, 1)
		this.setState({ selectedCocktails })	
	}

	searchCocktails = (event) => {
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

	submitOrder = () => {
		const { selectedCocktails } = this.state
		fetch('http://localhost:8080/cocktails/order', {
			method: 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json; charset=utf-8'
			},
			body: JSON.stringify(selectedCocktails),
		}).then(response => {
			if(response.ok) {
			    response.json().then(commande => {
						this.setState({ order: commande })
					})
			  }
		})
	}

  render(){
	  const {filteredCocktails, selectedCocktails, order} = this.state
	  return (
				<>
					<SearchBar values={filteredCocktails} onChange={this.searchCocktails} onDoubleClick={this.selectCocktail} />
					<br />
					{
						selectedCocktails.length > 0 ?
						<div>
							<CocktailsTable cocktails={selectedCocktails} removeCocktail={this.removeCocktail} />
							<button onClick={this.submitOrder}>Commander</button> <a href="/">Nouvelle commande</a>
							{ order.id ? <p>La commande #{order.id} ({order.prix} €) est en cours de préparation.</p> : '' }
						</div>
						: <br/>
					}
				</>
		  )
  }
}

export default Commande