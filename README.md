# dogpark

## Link: https://dog-park-micro.herokuapp.com/

GET dogpark (by id)
https://dog-park-micro.herokuapp.com/api/v1/dog_park/id/XXXX

GET dogpark (by name) 
https://dog-park-micro.herokuapp.com/api/v1/dog_park/name/XXXX

POST dogpark 
https://dog-park-micro.herokuapp.com/api/v1/dog_park/?name=XXXX&longitude=XXXX&latitude=XXXX

{ "name": "Not blank",
  "longitude": "Only positives",
  "latitude": "Only positives"
  }
  
PUT dogpark
https://dog-park-micro.herokuapp.com/api/v1/dog_park/