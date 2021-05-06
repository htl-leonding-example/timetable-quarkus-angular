import {Injectable, NgZone} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Teacher} from '../model/teacher.model';
import {Schoolclass} from '../model/schoolclass.model';
import {Unit} from '../model/unit.model';
import {SseService} from './sse.service';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  SERVER = 'http://localhost:8080/api/';

  constructor(private http: HttpClient, private sseService: SseService, private zone: NgZone) {
  }

  public getTeachers(): Observable<Teacher[]> {
    return this.http.get<Teacher[]>(this.SERVER + 'teacher');
  }

  public getSchoolclasses(): Observable<Schoolclass[]> {
    return this.http.get<Schoolclass[]>(this.SERVER + 'class');
  }

  public getUnitsPerClass(classid: string): Observable<Unit[]> {
    return this.http.get<Unit[]>(this.SERVER + 'unit/class/' + classid);
  }

  public saveUnit(unit: Unit): Observable<any> {
    console.log(this.SERVER + 'unit' + JSON.stringify(unit));
    return this.http.post(this.SERVER + 'unit', unit);
  }

  public deleteUnit(unit: Unit): Observable<any> {
    return this.http.delete(this.SERVER + 'unit' + unit.id);
  }

  public getServerSentEvent(): Observable<any> {
    return new Observable<any>(subscriber => {
        const eventSource = this.sseService.getEventSource(this.SERVER + 'unit/sse');

        eventSource.onmessage = ev => {
          this.zone.run(() => {
            console.log('Event: ' + ev);
            subscriber.next(ev.data);
          });
        };
        eventSource.onerror = ev => {
          this.zone.run(() => {
            console.log('Fehler: ' + ev);
            subscriber.error(ev);
          });
        };
      }
    );
  }
}
