import React from 'react'
import './App.css'

const CocktailsTable = ({cocktails, removeCocktail}) => (
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Nom</th>
				<th>Prix</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		{cocktails.map((cocktail, index) =>
			<tr key={index}>
				<td>{cocktail.id}</td>
				<td>{cocktail.nom}</td>
				<td>{cocktail.prix}</td>
				<td><button className="link" onClick={() => removeCocktail(index)}>&#10008;</button></td>
			</tr>
		)}
		</tbody>
	</table>
)

export default CocktailsTable