import { Authority } from './authority.enum';

export class User {

  constructor(
    public email: string,
    public role: Authority
  ) {
  }
}
