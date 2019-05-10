import React from 'react'
import './App.css'

const SearchBar = ({values, onChange, onDoubleClick}) => (
	<>
		<i>Rechercher un cocktail :</i>
		<div><input type="text" onChange={onChange} /></div>
		{
			values.length > 0 ?
			<div>
				<select multiple="multiple" size={Math.min(values.length, 10)} className="search-results">
					{values.map(cocktail => <option key={cocktail.id} onDoubleClick={onDoubleClick(cocktail)}>{cocktail.nom}</option>)}
				</select>
				<i> Double-cliquer sur un cocktail pour l’ajouter à la liste</i>
			</div>
			: <br/>
		}
	</>
)

export default SearchBar