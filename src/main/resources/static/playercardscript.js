`use strict`

import * as DOM from './dom.js';

// list item function
const writeItem = item => {
  const child = document.createElement(`li`);
  child.id = item._id;
  child.innerHTML = `${JSON.stringify(item)}`;
  DOM.listOutput.appendChild(child);
}



// GET all function
const get = () => {
  DOM.listOutput.innerHTML = ``;

  axios.get(`/read`)
    .then((response) => {
      if (!Array.isArray(response.data)) {
        writeItem(response.data);
      } else {
        for (let item of response.data) {
          writeItem(item);
        }
      }
    }).catch((err) => {
      console.log(err);
    });
}

// POST function
const post = () => {
  axios.post(`/create`, {   name : DOM.inputPlayerName.value,
                            age : DOM.inputPlayerAge.value, 
                            position : DOM.inputPlayerPosition.value})
    .then((response) => {
      console.log(response);
      get();
    }).catch((err) => {
      console.log(err);
    });
}

//del function
const deleteId = () => {
  let id = DOM.inputId.value
  axios.delete(`/delete/`+id)
  
  .then((response) => {
    console.log(response);
    get();
  }).catch((err) => {
    console.log(err);
  });

}

//update function
const updateId = () => {
  let id = DOM.updateId.value
  axios.put(`/update/`+id, {
    name : DOM.updatePlayerName.value,
    age : DOM.updatePlayerAge.value,
    position: DOM.updatePlayerPosition.value}) 
  .then((response) => {
    console.log(response);
    get();
  }).catch((err) => {
    console.log(err);
  });

}

//See single player

    
 



// set up the buttons' on click events
DOM.buttonCreate.onclick = () => post();
DOM.buttonDel.onclick = () => deleteId();
DOM.updateButton.onclick = () => updateId();
DOM.readButton.onclick = () => 





// run the get function on page load
get();