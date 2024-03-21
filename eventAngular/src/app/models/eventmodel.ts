// create class for event model
// export class EventModel {
//   id: number;
//   name: string;
//   date: string;
//   location: string;
//   desc: string;
// }
// Compare this snippet from src/app/whishlist/whishlist.component.html:
export class EventModel {
    id: number;
    name: string;
    date: string;
    location: string;
    desc: string;
    // add constructor
    constructor(id: number, name: string, date: string, location: string, desc: string) {
      this.id = id;
      this.name = name;
      this.date = date;
      this.location = location;
      this.desc = desc;
    }
}