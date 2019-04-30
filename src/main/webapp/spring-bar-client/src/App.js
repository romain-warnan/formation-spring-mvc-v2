import React from 'react'
import './App.css'

class App extends React.Component {
	
	constructor(props) {
		super(props)
		this.state = {
			cocktails: [],
		}
		this.handleChange = this.handleChange.bind(this)
	}
	
	handleChange(event) {
		const q = event.target.value
		const url = new URL('http://localhost:8080/cocktails/search')
		url.search = new URLSearchParams({ q })  
		fetch(url).then(response => {
			if(response.ok) {
			    response.json().then(cocktails => {
						this.setState({ cocktails })
					})
			  }
		})
	}
	
  render(){
	  const {cocktails} = this.state
	  return (
				<div>
					<input type="text" onChange={this.handleChange} />
					<ul>
					{cocktails.map((cocktail, index) => <li key={index}>{cocktail.nom}</li>)}
					</ul>
				</div>
		  )
  }
}

export default App