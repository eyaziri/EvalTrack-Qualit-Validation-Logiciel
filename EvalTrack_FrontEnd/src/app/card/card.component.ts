import { Component, Input  } from '@angular/core';

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss'
})
export class CardComponent {
  @Input() icon: string = '';
  @Input() title: string = '';
  @Input() count: number = 0;
  @Input() colorIcon :string='';

}
