import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpService} from '../service/http.service';
import {Schoolclass} from '../model/schoolclass.model';
import {Unit} from '../model/unit.model';
import {Teacher} from '../model/teacher.model';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-timetable',
  templateUrl: './timetable.component.html',
  styleUrls: ['./timetable.component.css']
})
export class TimetableComponent implements OnInit, OnDestroy {

  dayNames = ['MO', 'DI', 'MI', 'DO', 'FR'];

  classid = ' ';

  classes: Schoolclass[] = [];
  teachers: Teacher[] = [];
  units: Unit[] = [];

  sse: Subscription;

  constructor(private httpService: HttpService) {
  }

  ngOnInit(): void {
    this.httpService.getSchoolclasses().subscribe(value => {
      this.classes = value;
    });
    this.httpService.getTeachers().subscribe(value => {
      this.teachers = value;
    });

    this.sse = this.httpService.getServerSentEvent().subscribe(value => {
      this.unitChanged(JSON.parse(value));
    });
  }

  ngOnDestroy(): void {
    this.sse.unsubscribe();
  }

  onClassChanged(): void {
    this.httpService.getUnitsPerClass(this.classid).subscribe(value => {
      this.units = value;
    });
  }

  getActiveClass(): Schoolclass {
    for (const c of this.classes) {
      if (c.id === this.classid) {
        return c;
      }
    }

    return new Schoolclass();
  }

  getUnit(dayNumber: number, unitNumber: number): Unit {
    dayNumber++;
    for (const u of this.units) {
      if (u.day === dayNumber && u.unit === unitNumber) {
        return u;
      }
    }

    const newUnit = new Unit(0, dayNumber, unitNumber, 'frei', new Teacher(0, '---', '---', '---'), this.getActiveClass());
    this.units.push(newUnit);
    return newUnit;
  }

  getColor(u: Unit): string {
    let color = '';
    if (u.hasChanged) {
      color = 'green';
    }
    if (u.externallyChanged) {
      color = 'red';
    }
    return color;
  }

  save(): void {
    for (const u of this.units) {
      if (u.hasChanged) {
        if (u.teacher.id === 0) {
          this.httpService.deleteUnit(u).subscribe();
        } else {
          this.httpService.saveUnit(u).subscribe(value => u.hasChanged = false);
        }
      }
    }
  }

  unitChanged(unit: Unit): void {
    for (const u of this.units) {
      if (unit.schoolclass.id == u.schoolclass.id && unit.day === u.day && unit.unit === u.unit
        && (unit.subject !== u.subject || unit.teacher.id !== u.teacher.id)) {
        u.externallyChanged = true;
        u.teacher = unit.teacher;
        u.subject = unit.subject;
      }
    }
  }
}
