import React from 'react'
import './App.css'
import Commande from './Commande';
import Header from './Header';

class App extends React.Component {
  render(){
	  return (
			<>
				<Header/>
				<br />
				<Commande />
			</>
		)
  }
}

export default App