import {Schoolclass} from './schoolclass.model';
import {Teacher} from './teacher.model';

export class Unit {
  constructor(
    public id: number,
    public day: number,
    public unit: number,
    public subject: string,
    public teacher: Teacher,
    public schoolclass: Schoolclass,
    public hasChanged: boolean = false,
    public externallyChanged: boolean = false
  ) {
  }

}
